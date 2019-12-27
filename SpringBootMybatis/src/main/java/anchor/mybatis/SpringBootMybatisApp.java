package anchor.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("anchor.mybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApp.class, args);
    }
}