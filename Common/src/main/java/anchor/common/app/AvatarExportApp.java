package anchor.common.app;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author Anchor
 */
public class AvatarExportApp {
    //头像本地保存的路径
    final static String destination = "C:\\Users\\ancho\\Desktop\\avatar\\";

    public static void main(String[] args) throws Exception {
        downloadDept();
    }

    static int downloadUser() throws Exception{
        long start = System.currentTimeMillis();
        Db db = Db.use();
        //查出所有待下载用户的头像地址。用户下载量一般较小  无需开多线程下载
        List<Entity> list = db.query(userSql(userInitialize()));
        AtomicInteger success = new AtomicInteger(), fail = new AtomicInteger();
        executeDownload(list, success, fail);
        long elapseTime = System.currentTimeMillis() - start;
        statistic(success.get(), fail.get(), elapseTime);
        return list.size();
    }

    static int downloadDept() throws Exception{
        long start = System.currentTimeMillis();
        Db db = Db.use();
        List<Entity> list = db.findAll(Entity.create("muc_dept").set("dept_name_path", deptInitialize()).set("is_removed", 0));
        //先查出 deptSql() 所需的 dept_id_path 字段
        List<String> deptIdPath = list.stream().map(t -> (String) t.get("dept_id_path")).collect(Collectors.toList());
        List<Entity> allAvatar = new ArrayList<>();
        for (String s : deptIdPath) {
            List<Entity> query = db.query(deptSql(), s + "%");
            allAvatar.addAll(query);
        }
        db.closeConnection(db.getConnection());
        System.out.println("————————Starting download—————————");
        System.out.println("Estimate download num: " + allAvatar.size());
        List<List<Entity>> downloadList = subList(allAvatar, 200);
        AtomicInteger success = new AtomicInteger(), fail = new AtomicInteger();
        ExecutorService pool = Executors.newFixedThreadPool(10);
        //每200个文件分配一个线程去下载
        for (List<Entity> item : downloadList) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " start downloading!");
                executeDownload(item, success, fail);
            });
        }
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) break;
            Thread.sleep(3000);
        }
        long elapseTime = System.currentTimeMillis() - start;
        statistic(success.get(), fail.get(), elapseTime);
        return success.get();
    }

    /**
     * 初始化需要导出头像的用户uid
     */
    static List<String> userInitialize(){
//        String[] array = {"fanghb","wangjg","huziqiang","xiaomg","zhangxy11","lign",
//                "francoise","btyin","helmut.zodl","guym","wangjl"};
        String[] array = {"funyj", "zhaolei2", "scott", "peter", "avant", "xumf", "macp"};
        return ListUtil.toList(array);
    }

    /**
     * 初始化需要导出头像的部门
     */
    static List<String> deptInitialize() {
        List<String> dept = new ArrayList<>();
        dept.add("美的_美的集团_家用空调事业部");
        dept.add("美的_美的集团_生活电器事业部");
        dept.add("美的_美的集团_中央空调事业部");
        dept.add("美的_美的集团_厨房和热水事业部");
        dept.add("美的_美的集团_IOT事业部");
        dept.add("美的_美的集团_中央研究院");
        dept.add("美的_美的集团_制造技术研究院");
        return dept;
    }

    /**
     * 业务封装
     */
    static void executeDownload(List<Entity> item, AtomicInteger success, AtomicInteger fail) {
        for (Entity entity : item) {
            String uid = (String) entity.get("uid");
            String headPhoto = (String) entity.get("head_photo");
            try {
                download(uid, headPhoto);
            } catch (Exception e) {
                String log = "Download fail.uid = %s, url = %s, exception = %s.";
                System.out.println(String.format(log, uid, headPhoto, e.getMessage()));
                fail.getAndIncrement();
                continue;
            }
            success.getAndIncrement();
        }
    }

    /**
     * 查询用户头像地址的sql
     */
    static String userSql(List<String> users){
        String joint = "";
        for (String user : users) {
            joint += "'" + user + "',";
        }
        joint = joint.substring(0, joint.lastIndexOf(','));
        return "select emp.uid, ext.head_photo\n" +
                     "from muc_emp_ext ext,\n" +
                     "     muc_emp emp\n" +
                     "where emp.uid in (" + joint + ") and emp.id = ext.emp_id";
    }

    /**
     * 查询部门下用户头像地址的sql
     */
    static String deptSql() {
        return "with a as (select emp.id, emp.uid " +
                          "from muc_dept dept, " +
                               "muc_emp_dept_rel dept_rel, " +
                               "muc_emp emp " +
                          "where dept.id = dept_rel.dept_id " +
                            "and emp.id = dept_rel.emp_id " +
                            "and dept.dept_id_path like ? " +
                            "and emp_status = 2 " +
                            "and `rank` >= 13) " +
                "select distinct a.uid, b.head_photo " +
                "from muc_emp_ext b " +
                          "right join a on a.id = b.emp_id " +
                "where b.head_photo is not null;";
    }

    /**
     * 下载文件
     * @param fileName    下载后的文件名
     * @param avatarUrl   下载地址
     * @throws Exception
     */
    static void download(String fileName, String avatarUrl) throws Exception{
        String dir = destination + "%s.jpg";
        if (new File(String.format(dir, fileName)).exists()){
            throw new Exception("File '" + String.format(dir, fileName) + "' had existed");
        }
        URL url = new URL(avatarUrl);
        InputStream inputStream = url.openConnection().getInputStream();
        FileOutputStream out = new FileOutputStream(String.format(dir, fileName));
        int j = 0;
        while ((j = inputStream.read()) != -1) {
            out.write(j);
        }
        inputStream.close();
    }

    /**
     * 根据步长将List分为N个List
     *
     * @param step   步长
     */
    static <T> List<List<T>> subList(List<T> list, int step) {
        if (list == null || list.size() == 0 || step < 1) return Collections.emptyList();
        List<List<T>> result = new ArrayList<>();
        for (int i = 0; i < list.size(); ) {
            List<T> subList = list.subList(i, i = Math.min(i + step, list.size()));
            result.add(subList);
        }
        return result;
    }

    static void statistic(int success, int fail, long elapseTime) {
        System.out.println("————————————Statistic————————————");
        System.out.println("Total download: " + (success + fail));
        System.out.println("Successful download: " + success);
        System.out.println("Failed download: " + fail);
        System.out.println("Download time: " + timeFormat(elapseTime));
        System.out.println("———————————————end———————————————");
    }

    static String timeFormat(long time) {
        int total = (int) time / 1000;
        int min = total / 60;
        int sec = ((int)time - min * 60000) / 1000;
        return min > 0 ? (min + "m" + sec + "s") : sec + "s";
    }
}
