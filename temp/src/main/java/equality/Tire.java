package equality;

import java.util.Objects;

public class Tire {
    private int diameter;
    private int width;

    public Tire(int diameter, int width) {
        this.diameter = diameter;
        this.width = width;
    }

    public int getDiameter() {
        return diameter;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public boolean equals(Object o) {
//        if (o instanceof Tire) {
        if (o.getClass() == this.getClass()) {
            Tire tire = (Tire) o;
            return diameter == tire.diameter &&
                    width == tire.width;
        } else return false;
    }

    @Override
    public String toString() {
        return "Tire{" +
                "diameter=" + diameter +
                ", width=" + width +
                '}';
    }
}
