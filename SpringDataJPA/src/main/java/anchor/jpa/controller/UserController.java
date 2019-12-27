package anchor.jpa.controller;

import anchor.jpa.model.User;
import anchor.jpa.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/jpa")
public class UserController {

    @Resource
    private UserService usersService;

    @RequestMapping("/all")
    public List<User> getAllUsers() {
        return usersService.getAllByDefault();
    }

    //类似调用UsersService中方法即可
    //...
}
