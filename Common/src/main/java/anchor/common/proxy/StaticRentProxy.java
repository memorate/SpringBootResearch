package anchor.common.proxy;

import anchor.common.proxy.subject.Rent;

/**
 * @author Anchor
 */
public class StaticRentProxy implements Rent {

    private final Rent rent;

    public StaticRentProxy(Rent rent) {
        this.rent = rent;
    }

    @Override
    public void rentHouse() {
        System.out.println("I'm a rent proxy,i am looking for a suitable house for user...");
        rent.rentHouse();
        System.out.println("Rent successfully,i earned one billion...");
    }

    @Override
    public Rent getProxy() {
        return this;
    }
}
