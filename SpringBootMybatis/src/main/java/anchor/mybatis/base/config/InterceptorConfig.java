package anchor.mybatis.base.config;

import anchor.mybatis.base.interceptor.AnchorInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Anchor
 *
 * 拦截器配置类
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 注册两个拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 这里 addInterceptor() 的顺序就是实际上 Interceptor 执行的顺序
        registry.addInterceptor(new AnchorInterceptor.CustomInterceptor2())
                .addPathPatterns("/**");
        registry.addInterceptor(new AnchorInterceptor.CustomInterceptor1())
                .addPathPatterns("/**");
    }
}
