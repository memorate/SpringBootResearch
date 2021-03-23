package anchor.common.proxy;

import anchor.common.proxy.subject.Rent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDK 动态代理 Handler
 *
 * @author Anchor
 * @date 2021-03-23 16:12
 */
public class JdkDynamicRentProxy implements InvocationHandler {

    private final Rent client;

    public JdkDynamicRentProxy(Rent client) {
        this.client = client;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("JDK DynamicRentProxy start executing....");
        Object invoke = method.invoke(client, args);
        System.out.println("JDK DynamicRentProxy end executing....");
        return invoke;
    }
}
