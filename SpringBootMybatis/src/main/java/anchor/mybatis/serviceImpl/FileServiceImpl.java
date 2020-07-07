package anchor.mybatis.serviceImpl;

import anchor.mybatis.dto.UserDTO;
import anchor.mybatis.entity.User;
import anchor.mybatis.service.FileService;
import anchor.mybatis.service.UserService;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private HttpServletResponse response;

    @Resource
    private UserService userService;

    @Override
    public void exportAllUsers(){
        List<User> users = userService.getAllUser("id");
        List<UserDTO> collect = users.stream().map(UserDTO::new).collect(Collectors.toList());
        ExportParams params = new ExportParams(null, "用户信息", ExcelType.HSSF);
        try (Workbook wb = ExcelExportUtil.exportExcel(params, UserDTO.class, collect)) {
            this.response.setContentType("application/vnd.ms-excel");
            // 组装附件名称和格式
            this.response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("用户信息.xls", "UTF-8"));
            ServletOutputStream out = this.response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            log.error("导出异常", e);
//            throw new Exception("导出异常", e);
        }
    }
}
