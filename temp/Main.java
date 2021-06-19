class Tire {
  private int width;
  private int diameter;

  public Tire (int width, int diameter) {
    this.width = width;
    this.diameter = diameter;
  }

  public int getWidth() {
    return width;
  }

  public int getDiameter() {
    return diameter;
  }

  @Override
  public boolean equals(Object o) {
//    if (o instanceof Tire) {
    if (o.getClass() == this.getClass()) {
      Tire t = (Tire) o;
      return (this.width == t.width && this.diameter == t.diameter);
    }
    else return false;
  }

  @Override
  public String toString() {
    return "Tire{" +
      "width=" + width +
      ", diameter=" + diameter +
      '}';
  }
}

class TruckTire extends Tire {
  private int load;
  TruckTire(int width, int diameter, int load){
    super(width, diameter);
    this.load = load;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof TruckTire) {
      TruckTire t = (TruckTire) o;
      return (this.load == t.load && super.equals(t));
    }
    else return false;
  }
}

public class Main {
  public static void main(String[] args) {
    Tire t1 = new Tire(1, 1);
    Tire t2 = new Tire(1, 1);
    Tire t3 = new Tire(2, 2);
    System.out.println(t1.equals(t2));
    System.out.println(t1.equals(t3));

    TruckTire tt1 = new TruckTire(1, 1, 100);
    TruckTire tt2 = new TruckTire(1, 1, 100);
    TruckTire tt3 = new TruckTire(2, 2, 300);
    System.out.println(tt1.equals(tt2));
    System.out.println(tt1.equals(tt3));

    System.out.println(t1.equals(tt1));
    System.out.println(tt1.equals(t1));

  }
}