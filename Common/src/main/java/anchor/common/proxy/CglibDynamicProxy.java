package anchor.common.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB 动态代理实现类
 *
 * @author Anchor
 * @date 2021-03-23 16:33
 */
public class CglibDynamicProxy implements MethodInterceptor{

    public Object initCglib(Class target){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    /**
     * 这里如果使用如下写法，运行时会死循环并抛 StackOverflowError 错误
     *   Object invoke = method.invoke(o, objects)
     *
     * why？ ——> https://blog.csdn.net/weixin_33743661/article/details/91442498
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB DynamicRentProxy start executing....");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("CGLIB DynamicRentProxy start executing....");
        return invoke;
    }
}
