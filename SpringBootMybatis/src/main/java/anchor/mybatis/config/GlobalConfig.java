package anchor.mybatis.config;

import anchor.common.handler.GlobalExceptionController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @Bean
//    public GlobalExceptionHandler globalExceptionHandler(){
//        return new GlobalExceptionHandler();
//    }

    @Bean
    public GlobalExceptionController globalExceptionController(ErrorAttributes attributes) {
        return new GlobalExceptionController(attributes);
    }
}
