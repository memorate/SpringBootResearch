package anchor.mybatis.base.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Anchor
 */
@Slf4j
public class AnchorFilter {

    public static class CustomerFilter1 implements Filter {
        /**
         * init() 只在 SpringBoot 启动时初始化一次
         */
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("CustomerFilter1 initializing...");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("CustomerFilter1 info: request url = " + httpServletRequest.getRequestURL().toString());
            long startFilter1Time = System.currentTimeMillis();
            request.setAttribute("startFilter1Time", startFilter1Time);
            chain.doFilter(request, response);
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter1Time");
            log.info("CustomerFilter1 info: Elapse time = " + elapse + " ms");
        }

        /**
         * destroy() 只在 SpringBoot 关闭时摧毁一次
         */
        @Override
        public void destroy() {
            log.info("CustomerFilter1 destroying...");
        }
    }

    public static class CustomerFilter2 implements Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            log.info("CustomerFilter2 initializing...");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            log.info("CustomerFilter2 info: request url = " + httpServletRequest.getRequestURL().toString());
            long startFilter2Time = System.currentTimeMillis();
            request.setAttribute("startFilter2Time", startFilter2Time);
            chain.doFilter(request, response);
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter2Time");
            log.info("CustomerFilter2 info: Elapse time = " + elapse + " ms");
        }

        @Override
        public void destroy() {
            log.info("CustomerFilter2 destroying...");
        }
    }
}
