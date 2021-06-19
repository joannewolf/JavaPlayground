package simplethread;

public class Visibility {
    static volatile boolean stop = false;

    static class MyStopper implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello from worker");
            while (! stop)
                ;
            System.out.println("Worker ending...");
        }
    }

    public static void main(String[] args) throws Throwable {
        new Thread(new MyStopper()).start();
        Thread.sleep(1_000);
        stop = true;
        System.out.println("Stop set true, main exiting, stop value is " + stop);
    }
}
