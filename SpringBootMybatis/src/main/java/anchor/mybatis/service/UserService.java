package anchor.mybatis.service;

import anchor.mybatis.entity.User;
import anchor.mybatis.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public Long save(User user){
        userMapper.save(user);
        return user.getId();
    }

    public int deleteById(Long id){
        return userMapper.deleteById(id);
    }

    public List<User> getAllUser(){
        return userMapper.findAll();
    }

    public User getById(Long id){
        return userMapper.findOne(id);
    }

    public User getByName(String name){
        return userMapper.findByName(name);
    }

    public int update(User user){
        return userMapper.update(user);
    }
}
