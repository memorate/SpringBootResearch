package anchor.mybatis.service;

import anchor.mybatis.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void save() {
        List<User> users = new ArrayList<>();
        users.add(new User("Michale",30,"Previous leader."));
        users.add(new User("Tom",10,"The poor cat."));
        users.add(new User("Jerry",10,"The naughty mouse."));
        users.add(new User("Jack",50,"China's richest man."));
        users.add(new User("Anchor",18,"The handsomest boy in the universe."));
        userService.saveList(users);
    }

}