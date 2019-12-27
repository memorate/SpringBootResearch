package anchor.mybatis.controller;

import anchor.mybatis.entity.User;
import anchor.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/mybatis")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/saveOne")
    @ResponseBody
    public Long saveOne(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping("/deleteById")
    public int deleteById(@RequestParam Long id){
        return userService.deleteById(id);
    }

    @GetMapping("/getAll")
    public List<User> getAll(){
        return userService.getAllUser();
    }

    @GetMapping("/getById")
    public User getById(@RequestParam Long id){
        return userService.getById(id);
    }

    @GetMapping("/getByName")
    public User getByName(@RequestParam String name){
        return userService.getByName(name);
    }

    @PostMapping("/update")
    public int update(@RequestBody User user){
        return userService.update(user);
    }
}
