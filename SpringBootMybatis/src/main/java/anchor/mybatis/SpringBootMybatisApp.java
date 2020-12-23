package anchor.mybatis;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Anchor
 */
@Slf4j
@MapperScan("anchor.mybatis.mapper")
@SpringBootApplication
public class SpringBootMybatisApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApp.class, args);
        log.info("Anchor-Mybatis Researcher 启动成功...");
    }
}