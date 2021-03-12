package anchor.mybatis.base.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Anchor
 *
 * 拦截器定义类
 *
 * 1.拦截器基于 Java 反射机制来实现的
 * 2.拦截器在过滤器过滤之后进行拦截
 * 3.请求经过多个过滤器 + 拦截器的顺序:
 *   doFilter1() ——> doFilter2() ——> preHandle1() ——> preHandle2() ——> Controller  ——> postHandle1()
 *   ——> postHandle2() ——> afterCompletion1() ——>  afterCompletion2() ——> doFilter1() ——> doFilter2()
 *      PS: doFilter() 多次出现是指 chain.doFilter(request, response) 之前和之后的代码
 * 4.如果某个 preHandle 返回了 false，则请求直接结束
 * 5.{@link HandlerInterceptorAdapter} 相较于 {@link HandlerInterceptor} 多了一个 afterConcurrentHandlingStarted() 方法，
 *   它会在异步请求的情况下代替 postHandle() 和 afterCompletion()
 */
@Slf4j
public class AnchorInterceptor implements HandlerInterceptor {

    public static class CustomInterceptor1 implements HandlerInterceptor {

        public CustomInterceptor1() {
            log.info("CustomInterceptor1 info: initializing...");
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HandlerMethod handle = (HandlerMethod) handler;
            log.info("CustomInterceptor1 preHandle info: request url = " + request.getRequestURL() +
                    " handler = " + handle.getBean().getClass().getName() + handle.getMethod().getName());
            long startInterceptor1Time = System.currentTimeMillis();
            request.setAttribute("startInterceptor1Time", startInterceptor1Time);
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            log.info("CustomInterceptor1 postHandle info: request result = " + modelAndView);
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter1Time");
            log.info("CustomInterceptor1 afterCompletion info: Elapse time = " + elapse + " ms");
        }
    }

    public static class CustomInterceptor2 extends HandlerInterceptorAdapter {

        public CustomInterceptor2() {
            log.info("CustomInterceptor2 info: initializing...");
        }

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HandlerMethod handle = (HandlerMethod) handler;
            log.info("CustomInterceptor2 preHandle info: request url = " + request.getRequestURL() +
                    " handler = " + handle.getBean().getClass().getName() + handle.getMethod().getName());
            long startInterceptor1Time = System.currentTimeMillis();
            request.setAttribute("startInterceptor1Time", startInterceptor1Time);
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            log.info("CustomInterceptor2 postHandle info: request result = " + modelAndView);        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            long elapse = System.currentTimeMillis() - (long) request.getAttribute("startFilter1Time");
            log.info("CustomInterceptor2 afterCompletion info: Elapse time = " + elapse + " ms");
        }

        @Override
        public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info("CustomInterceptor2 afterConcurrentHandlingStarted info: hello!");
        }
    }
}
