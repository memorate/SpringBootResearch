package anchor.jpa.repository;

import anchor.jpa.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /*************************************普通约定方法使用*****************************************/
    /**
     * 四个普通的约定方法
     * <p>
     * find、read、query、get开头都可
     */
    User findByNameAndAge(String name, int age);

    List<User> readByAgeLessThanEqual(int age);

    List<User> queryByNameStartingWith(String word);

    List<User> getByDescriptionIsNotNull();

    /**
     * 约定方法进行删除，必须加@Transactional和@Modifying注解
     * <p>
     * 这两个注解加在调用此方法的方法上也可以
     */
    @Transactional
    @Modifying
    int deleteByAge(int age);


    /*************************************约定方法排序和分页*****************************************/

    /**
     * 约定方法 - 排序
     * <p>
     * 命名需按照约定，再传入Sort对象即可
     */
    List<User> findByAge(int age, Sort sort);

    /**
     * 约定方法 - 分页
     * <p>
     * 命名需按照约定，再传入实现Pageable接口的对象即可
     */
    Page<User> findByAge(int age, Pageable pageable);


    /*************************************HQL*****************************************/

    /**
     * 自定义方法模糊（like）查询
     * 必须在参数"?1"左右加上%
     */
    @Query("select u from User u where u.name like %?1%")
    List<User> findByNameLike(String word);

    /**
     * 使用"@Param"注解注入参数
     */
    @Query("select u from User u where u.name = :name and u.age = :age")
    User QFindByNameAndAge(@Param("name") String name, @Param("age") int age);


    /*************************************SQL*****************************************/

    /**
     * 使用原生sql
     */
    @Query(value = "SELECT * from users where name= ?1", nativeQuery = true)
    User findByName(String name);

    /**
     * 使用原生sql，且使用"@Param"注解注入参数
     */
    @Query(value = "SELECT * from users where age= :myAge", nativeQuery = true)
    User findByAge(@Param("myAge") int age);

    /**
     * 自定义方法进行更新操作，必须加@Transactional和@Modifying注解
     * <p>
     * 这两个注解加在调用此方法的方法上也可以
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE users set age=?1 where id=?2", nativeQuery = true)
    int updateSpecific(int age, long id);


    /*************************************自定义方法排序和分页*****************************************/

    /**
     * 自定方法 - 排序
     */
    @Query("select u from User u where u.age = ?1")
    List<User> findByAgeAndSort(int age, Sort sort);

    /**
     * 自定方法 - 分页34
     */
    @Query("select u from User u where u.age = ?1")
    Page<User> findByAgeAndPage(int age, Pageable pageable);
}
