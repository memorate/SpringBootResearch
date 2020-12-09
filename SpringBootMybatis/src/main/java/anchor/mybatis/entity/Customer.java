package anchor.mybatis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息 
 * </p>
 *
 * @author 
 * @since 2020-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -8701317935426813192L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户真实姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别，1-男、2-女、3-保密
     */
    private Integer gender;

    /**
     * 邮箱地址
     */
    private String mail;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 信息创建时间
     */
    private LocalDateTime createTime;

    /**
     * 信息更新时间
     */
    private LocalDateTime updateTime;

    public Customer(String name, Integer age, Integer gender, String mail, String province) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mail = mail;
        this.province = province;
    }
}
