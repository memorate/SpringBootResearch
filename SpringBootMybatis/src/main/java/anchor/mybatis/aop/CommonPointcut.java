package anchor.mybatis.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Anchor
 */
public class CommonPointcut {

    @Pointcut("execution(* anchor.mybatis.controller.CommonController.aopTest(..))")
    public void aopTestMethod() {}

    @Pointcut("bean(commonController)")
    public void targetAnnotation(){}

    @Pointcut("@annotation(anchor.mybatis.aop.LogTag)")
    public void annotation() {}
}
