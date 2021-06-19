package equality;

import java.util.Objects;

public class TruckTire extends Tire {
    private int load;

    public TruckTire(int diameter, int width, int load) {
        super(diameter, width);
        this.load = load;
    }

    public int getLoad() {
        return load;
    }

    @Override
    public boolean equals(Object o) {
//        if (o instanceof TruckTire) {
        if (o.getClass() == this.getClass()) {
            TruckTire truckTire = (TruckTire) o;
            return load == truckTire.load && super.equals(o);
        } else return false;
    }

    @Override
    public String toString() {
        return "TruckTire{" +
                "load=" + load +
                super.toString() +
                '}';
    }
}
