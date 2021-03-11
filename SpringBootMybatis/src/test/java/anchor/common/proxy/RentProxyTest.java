package anchor.common.proxy;

import org.junit.jupiter.api.Test;

/**
 * @author Anchor
 */
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