package anchor.mybatis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Anchor
 */
@Slf4j
@Component
public class AnchorPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnchorBean){
            log.info("----8.AnchorPostProcessor: BeanPostProcessor.postProcessBeforeInitialization() executing, beanName = {}", beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AnchorBean){
            log.info("----12.AnchorPostProcessor: BeanPostProcessor.postProcessAfterInitialization() executing, beanName = {}", beanName);
        }
        return bean;
    }
}
