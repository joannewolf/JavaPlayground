package simplethread;

class MyRunnable implements Runnable {
    int i = 0;
    @Override
    public void run() {
        for (; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + " i is " + i);
        }
    }
}
public class RunnableOne {
    public static void main(String[] args) {
        Runnable r = new MyRunnable();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
    }
}
