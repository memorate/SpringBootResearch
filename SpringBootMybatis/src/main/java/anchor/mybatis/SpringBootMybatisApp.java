package anchor.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("anchor.mybatis.mapper")
@SpringBootApplication(scanBasePackages = {"anchor.mybatis.*","anchor.common.*"})
public class SpringBootMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApp.class, args);
        log.info("Anchor-Mybatis Researcher start success...");
    }
}