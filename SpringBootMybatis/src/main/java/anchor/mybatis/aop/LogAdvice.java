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

    @Before("CommonPointcut.executionExp1()")
    public void before() {
        log.info("Before advice...");
    }

    @After("CommonPointcut.executionExp1()")
    public void after() {
        log.info("After advice...");
    }

    @AfterReturning(value = "CommonPointcut.executionExp1()", returning = "result")
    public void afterReturning(Object result) {
        log.info("AfterReturning advice, return value is {}...", result);
    }

    @AfterThrowing(value = "CommonPointcut.executionExp1()", throwing = "exception")
    public void afterReturning(Exception exception) {
        log.info("AfterThrowing advice, exception is {}...", exception.toString());
    }

    @Around(value = "CommonPointcut.executionExp1()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering around advice...");
        Object proceed = joinPoint.proceed();
        log.info("Exiting around advice...");
        return proceed;
    }
}
