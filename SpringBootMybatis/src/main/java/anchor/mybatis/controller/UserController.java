package anchor.mybatis.controller;

import anchor.mybatis.object.entity.User;
import anchor.mybatis.service.UserService;
import com.github.pagehelper.PageInfo;
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
    public Long saveOne(@RequestBody User user) {
        return userService.saveOne(user);
    }

    @DeleteMapping("/deleteById")
    public int deleteById(@RequestParam Long id) {
        return userService.deleteById(id);
    }

    @GetMapping("/findAll")
    public List<User> getAll(@RequestParam(required = false, defaultValue = "id") String orderBy) {
        return userService.getAllUser(orderBy);
    }

    @GetMapping("/getAll")
    public List<User> getAll(@RequestParam(required = false, defaultValue = "id") String column,
                             @RequestParam(required = false, defaultValue = "asc") String order) {
        if (!order.equalsIgnoreCase("asc") && !order.equalsIgnoreCase("desc")) {
            throw new IllegalArgumentException("\"order\" 必须是 \"asc\"或\"desc\"");
        }
        return userService.getAllUser(column, order);
    }

    @GetMapping("/getById")
    public User getById(@RequestParam Long id) {
        return userService.getById(id);
    }

    @GetMapping("/getByName")
    public List<User> getByName(@RequestParam String name) {
        return userService.getByName(name);
    }

    @GetMapping("/getByNameAndAge")
    public List<User> getByNameAndAge(@RequestParam String name, @RequestParam int age) {
        return userService.getByNameAndAge(name, age);
    }

    @GetMapping("/getByAge")
    public List<User> getByAge(@RequestParam String age) {
        return userService.getByAge(age);
    }

    @PostMapping("/getByUser")
    public List<User> getByUser(@RequestBody User user) {
        return userService.getByUser(user);
    }

    @PostMapping("/getByIds")
    public List<User> getByIds(@RequestBody List<Long> idList) {
        return userService.getByIds(idList);
    }

    @GetMapping("/pageQuery")
    public PageInfo<User> pageQuery(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                    @RequestParam(required = false, defaultValue = "4") int pageSize) {
        return userService.pageQuery(pageNumber, pageSize);
    }

    @PostMapping("/update")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }
}
