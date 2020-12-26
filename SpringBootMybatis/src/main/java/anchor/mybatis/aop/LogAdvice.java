package anchor.mybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author Anchor
 */
@Slf4j
@Aspect
@Component
public class LogAdvice {

    @Pointcut("execution(* anchor.mybatis.controller.CommonController.aopTest(..))")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        log.info("Before advice...");
    }

    @After("pointcut()")
    public void after() {
        log.info("After advice...");
    }

    @AfterReturning(value = "pointcut()",returning = "result")
    public void afterReturning(Object result) {
        log.info("AfterReturning advice, return value is {}...", result);
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Around advice...");
        return joinPoint.proceed();
    }
}
