package anchor.common.proxy;

public class RentProxy implements Rent{

    private Rent rent;

    public RentProxy(Rent rent) {
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
