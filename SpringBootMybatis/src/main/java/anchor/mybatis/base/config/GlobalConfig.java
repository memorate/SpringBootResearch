package anchor.mybatis.base.config;

import anchor.common.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

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

//    @Bean
//    public GlobalExceptionController globalExceptionController(DefaultErrorAttributes attributes) {
//        return new GlobalExceptionController(attributes);
//    }
}
