package anchor.mybatis.aop;


import anchor.common.response.BaseResponse;
import anchor.mybatis.entity.OperationLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Anchor
 *
 * uid uname resource method code message time
 */
@Slf4j
@Aspect
@Component
public class ResultRecorderAspect {
    @Around("CommonPointcut.resultRecorder()")
    public Object resultRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        OperationLog log = new OperationLog()
                .setTime(LocalDateTime.now());
        //SysUser user = SystemUserHolder.getCurrent()
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ResultRecorder annotation = joinPoint.getTarget().getClass().getAnnotation(ResultRecorder.class);
        log.setResource(annotation.value());
        log.setMethod(signature.toShortString());
        try {
            Object result = joinPoint.proceed();
            if (result instanceof BaseResponse) {
                BaseResponse response = (BaseResponse) result;
                log.setCode(response.getCode().code());
                log.setMessage(response.getMessage());
            } else {
                log.setCode(500);
                log.setMessage("Can't recognize the method's response.");
            }
            return result;
        } catch (Throwable throwable) {
            log.setCode(500);
            log.setMessage(throwable.getMessage());
            throw throwable;
        } finally {
            System.out.println(log);
        }
    }
}
