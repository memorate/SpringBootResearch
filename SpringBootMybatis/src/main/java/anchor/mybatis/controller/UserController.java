package anchor.mybatis.controller;

import anchor.common.page.Page;
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
    public Long saveOne(@RequestBody User user) {
        return userService.save(user);
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

    @PostMapping("/getByIds")
    public List<User> getByIds(@RequestBody List<Long> idList) {
        return userService.getByIds(idList);
    }

    @GetMapping("/pageQuery")
    public Page<User> pageQuery(@RequestParam(required = false, defaultValue = "1") int pageNumber,
                                @RequestParam(required = false, defaultValue = "4") int pageSize,
                                @RequestParam(required = false, defaultValue = "id") String orderBy) {
        return userService.pageQueryAll(pageNumber, pageSize, orderBy);
    }

    @PostMapping("/update")
    public int update(@RequestBody User user) {
        return userService.update(user);
    }
}
