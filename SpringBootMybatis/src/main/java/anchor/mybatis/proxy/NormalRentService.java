package anchor.mybatis.proxy;

/**
 * @author Anchor
 */
public class NormalRentService implements Rent {
    @Override
    public void rentHouse() {
        System.out.println("Rent a normal house to user...");
    }

    @Override
    public Rent getProxy() {
        return new RentProxy(this);
    }
}
