package anchor.mybatis.service;

import anchor.mybatis.mapper.UserDetailMapper;
import anchor.mybatis.mapper.UserMapper;
import anchor.mybatis.object.constant.UserColumn;
import anchor.mybatis.object.entity.User;
import anchor.mybatis.object.entity.UserDetail;
import anchor.mybatis.object.vo.MobileResponse;
import anchor.mybatis.object.vo.QRCodeResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ServiceTest {

    @Resource
    private UserService userService;

    @Resource
    private UserDetailService detailService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDetailMapper detailMapper;

    @Resource
    private CommonService commonService;

    @Test
    void saveUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Michale", 30, "Previous leader."));
        users.add(new User("Tom", 10, "The poor cat."));
        users.add(new User("Jerry", 10, "The naughty mouse."));
        users.add(new User("Jack", 50, "China's richest man."));
        users.add(new User("Anchor", 18, "The handsomest boy in the universe."));
        int size = userService.saveList(users);
        Assert.assertEquals(5, size);
    }

    @Test
    void saveDetails() {
        List<UserDetail> details = new ArrayList<>();
        details.add(new UserDetail(1L, "Beijing", "111111111", "Undergraduate"));
        details.add(new UserDetail(1L, "Shanghai", "222222222", "Master"));
        details.add(new UserDetail(2L, "Nanjing", "333333333", "undergraduate"));
        details.add(new UserDetail(2L, "Guangzhou", "444444444", "PhD"));
        details.add(new UserDetail(3L, "HonKong", "555555555", "Master"));
        details.add(new UserDetail(3L, "Wuhan", "666666666", "Undergraduate"));
        details.add(new UserDetail(4L, "Shenzhen", "777777777", "Master"));
        details.add(new UserDetail(4L, "Chengdu", "888888888", "Master"));
        int size = detailService.saveList(details);
        Assert.assertEquals(6, size);
    }

    @Test
    void pageHelperTest() {
        PageHelper.startPage(1, 2);
        List<User> users = userMapper.findAll(UserColumn.NAME);
        PageInfo pageInfo = new PageInfo<>(users);
        Assert.assertEquals(2, pageInfo.getSize());
    }

    @Test
    void getForString() {
        System.out.println(commonService.getForString("15927005006"));
    }

    @Test
    void getForResponse() {
        MobileResponse response = commonService.getForResponse("15927005006");
        System.out.println(response);
    }

    @Test
    void getForEntity() {
        ResponseEntity<MobileResponse> response = commonService.getForEntity("15927005006");
        System.out.println(response);
    }

    @Test
    void getForStringWithHeader() {
        String response = commonService.getForStringWithHeader("15927005006");
        System.out.println(response);
    }

    @Test
    void getForResponseWithHeader() {
        ResponseEntity<MobileResponse> response = commonService.getForEntityWithHeader("15927005006");
        System.out.println(response);
    }

    @Test
    void postForString() {
        String response = commonService.postForString("Anchor");
        System.out.println(response);
    }

    @Test
    void postForResponse() {
        QRCodeResponse response = commonService.postForResponse("Anchor");
        System.out.println(response);
    }

    @Test
    void postForResponseWithHeader() {
        QRCodeResponse response = commonService.postForResponseWithHeader("Anchor");
        System.out.println(response);
    }

    @Test
    void postForEntityWithHeader() {
        ResponseEntity<QRCodeResponse> response = commonService.postForEntityWithHeader("Anchor");
        System.out.println(response);
    }
}