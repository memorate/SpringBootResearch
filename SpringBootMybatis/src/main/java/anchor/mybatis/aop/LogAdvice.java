package anchor.mybatis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Anchor
 */
@Slf4j
@Aspect
@Component
public class LogAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void pointcut(){}

    @Before("pointcut()")
    public void logAdvice(){
        log.info("Entering RequestMapping...");
    }
}
