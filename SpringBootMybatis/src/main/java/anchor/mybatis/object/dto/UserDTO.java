package anchor.mybatis.object.dto;

import anchor.mybatis.object.entity.User;
import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anchor
 */
@Data
@NoArgsConstructor
public class UserDTO {

    @Excel(name = "序号", type = 10, width = 8)
    private Long id;

    @Excel(name = "姓名")
    private String name;

    @Excel(name = "年龄", type = 10)
    private Integer age;

    @Excel(name = "描述", width = 40)
    private String description;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
        this.description = user.getDescription();
    }
}
