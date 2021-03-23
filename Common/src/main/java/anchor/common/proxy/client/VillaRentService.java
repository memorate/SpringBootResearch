package anchor.common.proxy.client;

import anchor.common.proxy.StaticRentProxy;
import anchor.common.proxy.subject.Rent;

/**
 * 被代理的别墅租房服务
 *
 * @author Anchor
 */
public class VillaRentService implements Rent {
    @Override
    public void rentHouse() {
        System.out.println("Rent a villa to user...");
    }

    @Override
    public Rent getProxy() {
        return new StaticRentProxy(this);
    }
}
