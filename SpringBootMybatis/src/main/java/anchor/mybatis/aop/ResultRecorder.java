package anchor.mybatis.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Anchor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ResultRecorder {
    /**
     * RestFul中所操作的资源名，例：用户、订单、地址
     */
    String value();
}
