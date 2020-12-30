package anchor.mybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Anchor
 */
@Slf4j
@Aspect
@Component
public class LogAdvice {

    @Before("CommonPointcut.executionExp1()")
    public void before(JoinPoint joinPoint) {
        log.info("Before advice...");
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        System.out.println(joinPoint.toLongString());
        System.out.println(joinPoint.getSignature().toLongString());
    }

    @After("execution(* anchor.mybatis.controller.CommonController.aopTest(..))")
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
    public Object around(ProceedingJoinPoint joinPoint) {
        log.info("Entering around advice...");
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            proceed = throwable;
            log.info("Around catch an exception,exception is {}...", throwable.toString());
        }
        log.info("Exiting around advice...");
        return proceed;
    }
}
