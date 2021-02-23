package anchor.mybatis.vo;

import anchor.mybatis.aop.EnumValidate;
import anchor.mybatis.constant.Gender;
import anchor.mybatis.validation.InsertGroup;
import anchor.mybatis.validation.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author Anchor
 */
@Data
public class Validation {
    @Max(10)
    private int num;
    @NotBlank
    private String blank;
    @EnumValidate(target = Gender.class, groups = {InsertGroup.class, UpdateGroup.class})
    private String gender;
}
