package anchor.mybatis.mapper;

import anchor.mybatis.object.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {

    int saveOne(User user);

    int saveList(List<User> users);

    int deleteById(Long id);

    List<User> findAll(String orderBy);

    List<User> getAll(Map<String, String> param);

    User findOne(Long id);

    List<User> findByName(String name);

    List<User> findByNameAndAge(String name, int age);

    List<User> findByUser(User user);

    List<User> findByIds(List<Long> idList);

    int update(User user);

    List<User> findByAge(String age);
}
