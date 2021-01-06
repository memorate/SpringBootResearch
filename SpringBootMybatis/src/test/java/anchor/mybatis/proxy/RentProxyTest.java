package anchor.mybatis.proxy;

import org.junit.jupiter.api.Test;

class RentProxyTest {

    @Test
    void proxyTest1() {
        NormalRentService service = new NormalRentService();
        RentProxy proxy = new RentProxy(service);
        proxy.rentHouse();
    }

    @Test
    void proxyTest2() {
        VillaRentService service = new VillaRentService();
        RentProxy proxy = new RentProxy(service);
        proxy.rentHouse();
    }
}