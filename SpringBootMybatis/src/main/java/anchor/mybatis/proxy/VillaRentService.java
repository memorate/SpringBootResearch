package anchor.mybatis.proxy;

/**
 * @author Anchor
 */
public class VillaRentService implements Rent {
    @Override
    public void rentHouse() {
        System.out.println("Rent a villa to user...");
    }
}
