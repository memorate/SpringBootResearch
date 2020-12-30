package anchor.mybatis.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Anchor
 */
@Slf4j
@Aspect
@Component
public class ResultRecorderAspect {
    @After("CommonPointcut.resultRecorder()")
    public void resultRecord(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ResultRecorder annotation = signature.getMethod().getAnnotation(ResultRecorder.class);
    }
}
