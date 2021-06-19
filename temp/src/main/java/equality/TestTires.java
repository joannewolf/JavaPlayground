package equality;

public class TestTires {
    public static void main(String[] args) {
        Tire t1 = new Tire(15, 155);
        Tire t2 = new Tire(15, 155);
        Tire t3 = new Tire(15, 185);
        System.out.println("t1 - t2 " + (t1.equals(t2)));
        System.out.println("t1 - t3 " + (t1.equals(t3)));

        TruckTire tt1 = new TruckTire(15, 155, 1000);
        TruckTire tt2 = new TruckTire(15, 155, 1000);
        TruckTire tt3 = new TruckTire(15, 155, 2000);

        System.out.println("tt1 - tt2 " + tt1.equals(tt2));
        System.out.println("tt1 - tt3 " + tt1.equals(tt3));

        System.out.println("t1 - tt1 " + t1.equals(tt1));
        System.out.println("tt1 - t1 " + tt1.equals(t1));
    }
}
