package anchor.mybatis.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Anchor
 *
 * SpringBoot Bean 的生命周期如下:
 *   1.———————— SpringBoot 开始运行 ————————
 *   2.Bean 的静态方法快
 *   3.Bean 的普通方法快
 *   4.Bean 的构造方法
 *   5.Bean 的 setter 方法
 *   6.{@link BeanNameAware}.setBeanName()
 *   7.{@link BeanFactoryAware}.setBeanFactory()
 *   8.{@link ApplicationContextAware}.setApplicationContext()
 *   9.{@link BeanPostProcessor}.postProcessBeforeInitialization()
 *   10.{@link PostConstruct} 标注的方法
 *   11.{@link InitializingBean}.afterPropertiesSet()
 *   12.{@link Bean}.initMethod()
 *   13.{@link BeanPostProcessor}.postProcessAfterInitialization()
 *   14.———————— SpringBoot 停止运行 ————————
 *   15.{@link PreDestroy} 标注的方法
 *   16.{@link DisposableBean}.destroy()
 *   17.{@link Bean}.destroyMethod()
 *
 *  上面是 SpringBoot 中 Bean 的核心接口和生命周期，但是除上述之外，还有另外的初始化过程涉及的接口，详见：
 *     https://juejin.cn/post/6917398146268004365
 *  (或者见{@link BeanFactory} 的注释)
 */
@Slf4j
public class AnchorBean implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String param;

    static {
        log.info("----1.AnchorBean: static code block executing...");
    }

    {
        log.info("----2.AnchorBean: normal code block executing...");
    }

    public AnchorBean() {
        log.info("----3.AnchorBean: constructor executing...");
    }

    @Override
    public void setBeanName(String name) {
        log.info("----5.AnchorBean: BeanNameAware.setBeanName() executing, name = {}", name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("----6.AnchorBean: BeanFactoryAware.setBeanFactory() executing, beanFactory = {}", beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("----7.AnchorBean: ApplicationContextAware.setApplicationContext() executing, applicationContext = {}", applicationContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("----10.AnchorBean: InitializingBean.afterPropertiesSet() executing...");
    }

    @Override
    public void destroy() throws Exception {
        log.info("----14.AnchorBean: DisposableBean.DisposableBean() executing...");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("----9.AnchorBean: @postConstruct executing...");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("----13.AnchorBean: @preDestroy executing...");
    }

    public void myConstruct() {
        log.info("----11.AnchorBean: myConstruct executing...");
    }

    public void myDestroy() {
        log.info("----15.AnchorBean: myDestroy executing...");
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
        log.info("----4.AnchorBean: setParam executing, param = {}", param);
    }
}
