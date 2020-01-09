package anchor.mybatis.service;

import anchor.common.page.Page;
import anchor.mybatis.entity.User;
import anchor.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Long save(User user) {
        userMapper.save(user);
        return user.getId();
    }

    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    public List<User> getAllUser(String orderBy) {
        return userMapper.findAll(orderBy);
    }

    public List<User> getAllUser(String column, String order) {
        Map<String, String> param = new HashMap<>();
        param.put("column", column);
        param.put("order", order);
        return userMapper.getAll(param);
    }

    public User getById(Long id) {
        return userMapper.findOne(id);
    }

    public List<User> getByName(String name) {
        return userMapper.findByName(name);
    }

    public List<User> getByNameAndAge(String name, int age){
        return userMapper.findByNameAndAge(name,age);
    }

    public List<User> getByAge(String age){
        return userMapper.findByAge(age);
    }

    public List<User> getByUser(User user){
        return userMapper.findByUser(user);
    }

    public List<User> getByIds(List<Long> idList) {
        return userMapper.findByIds(idList);
    }

    public Page<User> pageQueryAll(int pageNumber, int pageSize, String orderBy) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("pageNumber", pageNumber - 1);
        map.put("pageSize", pageSize);
        map.put("orderBy", orderBy);
        List<User> users = userMapper.pageQueryUser(map);
        int totalUserNum = userMapper.findTotalUserNum();
        Page<User> page = new Page<>(pageNumber, pageSize);
        page.setResult(users);
        page.setTotalRecord(totalUserNum);
        page.setOrderBy(orderBy);
        return page;
    }

    public int update(User user) {
        return userMapper.update(user);
    }
}