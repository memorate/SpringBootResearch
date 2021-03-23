package anchor.common.proxy;

import anchor.common.proxy.client.NormalRentService;
import anchor.common.proxy.client.VillaRentService;
import anchor.common.proxy.subject.Rent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * @author Anchor
 */
class RentProxyTest {

    /**
     * 静态代理测试一
     */
    @Test
    void staticNormalRentTest() {
        StaticRentProxy proxy = (StaticRentProxy) new NormalRentService().getProxy();
        proxy.rentHouse();
    }

    /**
     * 静态代理测试二
     */
    @Test
    void staticVillaRentTest() {
        StaticRentProxy proxy = new StaticRentProxy(new VillaRentService().getProxy());
        proxy.rentHouse();
    }

    /**
     * JDK 动态代理测试
     */
    @Test
    void jdkDynamicProxyTest() {
        JdkDynamicRentProxy handler1 = new JdkDynamicRentProxy(new NormalRentService());
        JdkDynamicRentProxy handler2 = new JdkDynamicRentProxy(new VillaRentService());
        Rent rent1 = (Rent) Proxy.newProxyInstance(NormalRentService.class.getClassLoader(), new Class[]{Rent.class}, handler1);
        Rent rent2 = (Rent) Proxy.newProxyInstance(NormalRentService.class.getClassLoader(), new Class[]{Rent.class}, handler2);
        rent1.rentHouse();
        rent2.rentHouse();
    }

    @Test
    void cglibDynamicProxyTest() {
        CglibDynamicProxy proxy = new CglibDynamicProxy();
        Rent rent1 = (Rent) proxy.initCglib(NormalRentService.class);
        rent1.rentHouse();
        Rent rent2 = (Rent) proxy.initCglib(VillaRentService.class);
        rent2.rentHouse();
    }
}