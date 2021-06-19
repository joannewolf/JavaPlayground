package thread;

/**
 * Threads might interrupt each other...
 */
public class ThreadExample {
  class Worker implements Runnable {
    @Override
    public void run() {
      for (int i = 1; i < 100; i++) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
      }
    }
  }

  class WorkerWithInstanceVar implements Runnable {
    private int i;
    @Override
    public void run() {
      for (i = 1; i < 1000; i++) {
        System.out.println(Thread.currentThread().getName() + ": " + i);
      }
    }
  }

  public static void main(String[] args) {
    new ThreadExample().goThread2();
  }

  void goRunnable() {
    Worker worker1 = new Worker();
    Worker worker2 = new Worker();

    worker1.run();
    worker2.run();
//    The worker2 didn't start until worker1 finish
  }

  void goThread() {
    Worker worker = new Worker();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
//    The 2 threads run independently with their counting
  }

  void goThread2() {
    WorkerWithInstanceVar worker = new WorkerWithInstanceVar();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
//    The 2 threads interrupt counting with each other...
  }

  void goThread3() {
    WorkerWithInstanceVar worker1 = new WorkerWithInstanceVar();
    Thread thread1 = new Thread(worker1);
    WorkerWithInstanceVar worker2 = new WorkerWithInstanceVar();
    Thread thread2 = new Thread(worker2);

    thread1.start();
    thread2.start();
//    The 2 threads run independently with their counting
  }
}
