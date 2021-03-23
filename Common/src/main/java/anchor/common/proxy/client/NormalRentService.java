package anchor.common.proxy.client;

import anchor.common.proxy.StaticRentProxy;
import anchor.common.proxy.subject.Rent;

/**
 * 被代理的普通租房服务
 *
 * @author Anchor
 */
public class NormalRentService implements Rent {
    @Override
    public void rentHouse() {
        System.out.println("Rent a normal house to user...");
    }

    @Override
    public Rent getProxy() {
        return new StaticRentProxy(this);
    }
}