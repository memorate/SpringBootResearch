package anchor.mybatis.service;

import anchor.common.page.Page;
import anchor.mybatis.entity.User;

import java.util.List;

public interface UserService {
    Long save(User user);

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

    Page<User> pageQueryAll(int pageNumber, int pageSize, String orderBy);

    Integer update(User user);
}
