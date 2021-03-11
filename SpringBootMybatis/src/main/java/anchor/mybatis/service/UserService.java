package anchor.mybatis.service;

import anchor.mybatis.object.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Anchor
 */
public interface UserService {
    Long saveOne(User user);

    Integer saveList(List<User> users);

    Integer deleteById(Long id);

    List<User> getAllUser(String orderBy);

    List<User> getAllUser(String column, String order);

    User getById(Long id);

    List<User> getByName(String name);

    List<User> getByNameAndAge(String name, int age);

    List<User> getByAge(String age);

    List<User> getByUser(User user);

    List<User> getByIds(List<Long> idList);

    PageInfo<User> pageQuery(int pageNumber, int pageSize);

    Integer update(User user);
}
