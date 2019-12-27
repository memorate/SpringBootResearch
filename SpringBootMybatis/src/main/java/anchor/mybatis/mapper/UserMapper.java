package anchor.mybatis.mapper;

import anchor.mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    int save(User user);

    int deleteById(Long id);

    List<User> findAll();

    User findOne(Long id);

    User findByName(String name);

    int update(User user);
}
