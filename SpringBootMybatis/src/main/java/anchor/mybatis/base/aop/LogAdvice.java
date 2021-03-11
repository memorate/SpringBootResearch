package anchor.mybatis.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
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
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println(signature.toLongString());
        System.out.println(signature.toShortString());
        System.out.println(signature.getReturnType().getTypeName());
        System.out.println(signature.getName());
        System.out.println(signature.getDeclaringType().getName());
    }

    @After("execution(* anchor.mybatis.controller.CommonController.exportUsers(..))")
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
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            log.info("Around catch an exception,exception is {}...", throwable.toString());
            throw throwable;
        }finally {
            log.info("Exiting around advice...");
        }
    }
}
