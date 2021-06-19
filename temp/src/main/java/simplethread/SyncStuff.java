package simplethread;

public class SyncStuff {
    static int count = 0;
    static Object rendezvous = new Object();

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++)
                synchronized (rendezvous) {
                    count++;
                }
        }
    }

    static class MyRunnable2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100_000; i++)
                synchronized (rendezvous) {
                    count++;
                }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable2()).start();
        Thread.sleep(1000);
        System.out.println(count);
    }

}
