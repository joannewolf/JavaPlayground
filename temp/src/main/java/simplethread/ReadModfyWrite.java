package simplethread;

public class ReadModfyWrite {
    static class MyRunnable implements Runnable {
        /*volatile */int count = 0;

        @Override
        public void run() {
//            Object o = new Object();
//            System.out.println("Thread starting, using " + o + " for synchronization");

            for (int i = 0; i < 100_000; i++)
//                synchronized (o) {
                synchronized (this) {
                    count++;
                }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr);
        t1.start();
        Thread t2 = new Thread(mr);
        t2.start();
//        Thread.sleep(1000);
//        while (t1.isAlive()) ;
//        while (t2.isAlive()) ;
        t1.join();
        t2.join();
        System.out.println(mr.count);

    }

}
