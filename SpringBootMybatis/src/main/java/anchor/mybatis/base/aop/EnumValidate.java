package anchor.mybatis.base.aop;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.util.Arrays;

/**
 * @author Anchor
 *
 * validation 中用于枚举类型校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = EnumValidate.Validator.class)
public @interface EnumValidate {

    String message() default "枚举校验失败!";

    Class<?>[] groups() default {};

    Class<? extends BaseEnum> target();

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<EnumValidate, String> {
        Class<? extends BaseEnum> item;

        @Override
        public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
            BaseEnum[] enums = item.getEnumConstants();
            return Arrays.stream(enums)
                    .map(BaseEnum::getValue)
                    .anyMatch(t -> t.equals(s));
        }

        @Override
        public void initialize(EnumValidate constraintAnnotation) {
            item = constraintAnnotation.target();
        }
    }
}
