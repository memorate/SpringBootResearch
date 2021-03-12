package anchor.mybatis.base.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Anchor
 *
 * 过滤器定义类
 *
 * 1.Filter 是基于函数回调来实现的
 * 2.在自定义的 Filter 中无法 @Resource 注入 Bean，原因是：
 *     SpringBoot 启动时各组件的初始化顺序是 Listener ——> Filter ——> Servelet，普通 Bean 的初始化是在 Servelet 中
 */
@Slf4j
public class AnchorFilter {

    public static class CustomFilter1 implements Filter {
        /**
         * init() 只在 SpringBoot 启动时初调用一次
         */
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("CustomFilter1 info: initializing...");
        }

        /**
         * doFilter() 在每次请求时调用
         */
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("CustomFilter1 info: request url = " + httpServletRequest.getRequestURL().toString());
            long startFilter1Time = System.currentTimeMillis();
            request.setAttribute("startFilter1Time", startFilter1Time);
            chain.doFilter(request, response);
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter1Time");
            log.info("CustomFilter1 info: Elapse time = " + elapse + " ms");
        }

        /**
         * destroy() 只在 SpringBoot 关闭时调用一次
         */
        @Override
        public void destroy() {
            log.info("CustomFilter1 info: destroying...");
        }
    }

    public static class CustomFilter2 implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("CustomFilter2 info: initializing...");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("CustomFilter2 info: request url = " + httpServletRequest.getRequestURL().toString());
            long startFilter2Time = System.currentTimeMillis();
            request.setAttribute("startFilter2Time", startFilter2Time);
            chain.doFilter(request, response);
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter2Time");
            log.info("CustomFilter2 info: Elapse time = " + elapse + " ms");
        }

        @Override
        public void destroy() {
            log.info("CustomFilter2 info: destroying...");
        }
    }
}
