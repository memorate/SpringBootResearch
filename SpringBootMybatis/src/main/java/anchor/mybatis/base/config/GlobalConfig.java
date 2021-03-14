package anchor.mybatis.base.config;

import anchor.common.handler.GlobalExceptionHandler;
import anchor.mybatis.base.AnchorBean;
import anchor.mybatis.base.filter.AnchorFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;

/**
 * @author Anchor
 *
 * 全局普通 Bean 配置类
 */
@Configuration
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public FilterRegistrationBean<Filter> customFilter1() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AnchorFilter.CustomFilter1());
        registrationBean.setName("CustomFilter1");
        // 过滤器的顺序
        registrationBean.setOrder(100);
        // 过滤器作用的请求地址，"/*" 代表所有
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    /**
     * 注册了两个自定义 Filter，来观察他们的 order
     */
    @Bean
    public FilterRegistrationBean<Filter> customFilter2() {
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AnchorFilter.CustomFilter2());
        registrationBean.setName("CustomFilter2");
        registrationBean.setOrder(5);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    @Bean(initMethod = "myConstruct", destroyMethod = "myDestroy")
    public AnchorBean anchorBean() {
        AnchorBean bean = new AnchorBean();
        bean.setParam("My name is AnchorBean");
        return bean;
    }

//    @Bean
//    public GlobalExceptionController globalExceptionController(DefaultErrorAttributes attributes) {
//        return new GlobalExceptionController(attributes);
//    }
}
