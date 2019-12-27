package anchor.jpa.service;

import anchor.jpa.model.User;
import anchor.jpa.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 实际中Service类中不仅只是单单调用Repository中的各种方法，还包括大量的业务逻辑
 */
@Component
public class UserService {

    @Resource
    private UserRepository usersRepository;

    /**
     * 使用默认方法 - 新增或更新一条数据
     */
    public User saveUser(User user) {
        return usersRepository.save(user);
    }

    /**
     * 使用默认方法 - 新增或更新N调数据
     */
    public List<User> saveUserList(List<User> list) {
        return usersRepository.saveAll(list);
    }

    /**
     * 使用默认方法 - 根据id删除一条记录
     */
    public void deleteById(long id) {
        usersRepository.deleteById(id);
    }

    /**
     * 使用默认方法 - 查询所有User
     */
    public List<User> getAllByDefault() {
        return usersRepository.findAll();
    }

    /**
     * 使用默认方法 - 查询所有User且根据age增序排序
     */
    public List<User> getAllSortByDefault() {
        Sort sort = Sort.by(Sort.Direction.ASC, "age");
        return usersRepository.findAll(sort);
    }

    /**
     * 使用默认方法 - 分页查询User且按id增序排序
     *
     * @return 返回 Page<User>
     */
    public Page<User> getAllPageByDefault(int pageNum, int pageSize) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        return usersRepository.findAll(pageRequest);
    }

    /**
     * 使用约定方法 - 根据age查询UserList且根据id增序排序
     */
    public List<User> readAllSortByDefault(int age) {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return usersRepository.findByAge(age, sort);
    }

    /**
     * 使用约定方法 - 根据age分页查询UserPage且按name字母顺序增序排序
     *
     * @return 返回 Page<User>
     */
    public Page<User> readAllPageByDefault(int pageNum, int pageSize, int age) {
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sort);
        return usersRepository.findByAge(age, pageRequest);
    }

    /**
     * 使用自定义方法 - 根据name进行模糊查询
     */
    public List<User> getByNameLike(String word) {
        return usersRepository.findByNameLike(word);
    }

    /**
     * 使用自定义方法 - 根据自定义条件进行查询
     */
    public User deleteByName(String name, int age) {
        return usersRepository.QFindByNameAndAge(name, age);
    }


    /**
     * 使用自定义方法 - 根据age查询UserList且先根据id增序、再按age降序排序
     *
     * @return
     */
    public List<User> multiSort(int age) {
        Sort.Order idOrder = Sort.Order.asc("id");
        Sort.Order nameOrder = Sort.Order.desc("age");
        return usersRepository.findByAgeAndSort(age, Sort.by(idOrder, nameOrder));
    }

    /**
     * 使用自定义方法 - 根据age查询UserList且先根据name长度排序
     *
     * @return
     */
    public List<User> sortByLength(int age) {
        JpaSort jpaSort = JpaSort.unsafe(Sort.Direction.ASC, "LENGTH(name)");
        return usersRepository.findByAgeAndSort(age, jpaSort);
    }

}
