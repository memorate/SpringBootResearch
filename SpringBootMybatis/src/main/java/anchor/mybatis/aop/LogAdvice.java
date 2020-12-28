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

    @Before("CommonPointcut.aopTestMethod()")
    public void before() {
        log.info("Before advice...");
    }

    @After("CommonPointcut.aopTestMethod()")
    public void after() {
        log.info("After advice...");
    }

    @AfterReturning(value = "CommonPointcut.aopTestMethod()", returning = "result")
    public void afterReturning(Object result) {
        log.info("AfterReturning advice, return value is {}...", result);
    }

    @Around(value = "CommonPointcut.aopTestMethod()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Entering around advice...");
        Object proceed = joinPoint.proceed();
        log.info("Existing around advice...");
        return proceed;
    }
}
