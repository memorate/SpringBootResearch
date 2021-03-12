package anchor.mybatis.base.config;

import anchor.common.handler.GlobalExceptionHandler;
import anchor.mybatis.base.filter.AnchorFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.servlet.Filter;
import java.util.Collections;

/**
 * @author Anchor
 */
@Configuration
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

    @Bean
    public FilterRegistrationBean<Filter> anchorFilter1(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AnchorFilter.CustomerFilter1());
        registrationBean.setName("CustomerFilter1");
        // 过滤器的顺序
        registrationBean.setOrder(100);
        // 过滤器作用的请求地址，"/*" 代表所有
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> anchorFilter2(){
        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AnchorFilter.CustomerFilter2());
        registrationBean.setName("CustomerFilter2");
        registrationBean.setOrder(5);
        registrationBean.setUrlPatterns(Collections.singletonList("/*"));
        return registrationBean;
    }

//    @Bean
//    public GlobalExceptionController globalExceptionController(DefaultErrorAttributes attributes) {
//        return new GlobalExceptionController(attributes);
//    }
}
