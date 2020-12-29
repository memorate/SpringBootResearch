package anchor.mybatis.aop;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Anchor
 */
public class CommonPointcut {

    @Pointcut("execution(* anchor.mybatis.controller.CommonController.aopTest(..))")
    public void executionExp1() {}

    @Pointcut("execution(String anchor.mybatis..*(*,String))")
    public void executionExp2() {}

    @Pointcut("execution(anchor.common.response.BaseResponse anchor.mybatis..get*(*))")
    public void executionExp3() {}

    @Pointcut("within(anchor.mybatis.controller.CommonController)")
    public void withinExp(){}

    @Pointcut("this(anchor.mybatis.controller.CommonController)")
    public void thisExp(){}

    @Pointcut("target(anchor.mybatis.controller.CommonController)")
    public void targetExp(){}

    @Pointcut("args(*,*)")
    public void argsExp(){}

    @Pointcut("@target(LogTag)")
    public void targetAnnExp(){}

    @Pointcut("@args(*,*,LogTag)")
    public void argsAnnExp(){}

    @Pointcut("@within(LogTag)")
    public void withinAnnExp(){}

    @Pointcut("@annotation(anchor.mybatis.aop.LogTag)")
    public void annotationExp() {}

    @Pointcut("bean(commonController)")
    public void beanExp(){}
}
