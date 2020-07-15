package anchor.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@MapperScan("anchor.mybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApp.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}