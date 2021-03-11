package anchor.mybatis.object.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anchor
 */
@Data
@NoArgsConstructor
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String description;

    public User(String name, Integer age, String description) {
        this.name = name;
        this.age = age;
        this.description = description;
    }
}
