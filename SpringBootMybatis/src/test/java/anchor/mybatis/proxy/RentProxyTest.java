package anchor.mybatis.proxy;

import org.junit.jupiter.api.Test;

class RentProxyTest {

    @Test
    void normalRentTest() {
        RentProxy proxy = (RentProxy) new NormalRentService().getProxy();
        proxy.rentHouse();
    }

    @Test
    void villaRentTest() {
        RentProxy proxy = new RentProxy(new VillaRentService().getProxy());
        proxy.rentHouse();
    }
}