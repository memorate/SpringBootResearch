package anchor.mybatis.base.aop;


import anchor.common.response.BaseResponse;
import anchor.mybatis.object.entity.OperationLog;
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
@SuppressWarnings("all")
public class ResultRecorderAspect {
    @Around("CommonPointcut.resultRecorder()")
    public Object resultRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        OperationLog operation = new OperationLog()
                .setTime(LocalDateTime.now());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ResultRecorder annotation = joinPoint.getTarget().getClass().getAnnotation(ResultRecorder.class);
        operation.setResource(annotation.value());
        operation.setMethod(signature.toShortString());
        try {
            Object result = joinPoint.proceed();
            if (result instanceof BaseResponse) {
                BaseResponse response = (BaseResponse) result;
                operation.setCode(response.getCode().code());
                operation.setMessage(response.getMessage());
            } else {
                operation.setCode(500);
                operation.setMessage("Can't recognize the method's response.");
            }
            return result;
        } catch (Throwable throwable) {
            operation.setCode(500);
            operation.setMessage(throwable.getMessage());
            throw throwable;
        } finally {
            log.info(operation.toString());
        }
    }
}
