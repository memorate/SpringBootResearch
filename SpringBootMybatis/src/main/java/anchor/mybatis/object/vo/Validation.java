package anchor.mybatis.object.vo;

import anchor.mybatis.base.aop.EnumValidate;
import anchor.mybatis.base.validation.InsertGroup;
import anchor.mybatis.base.validation.UpdateGroup;
import anchor.mybatis.object.constant.Gender;
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
