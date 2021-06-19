package thread;

/**
 * Volatile doesn't guarantee atomicity, try synchronize!
 */
public class ThreadExample3 {
  class Worker implements Runnable {
    int count = 0;
    @Override
    public void run() {
      for (int i = 0; i < 1000_000; i++)
        count ++;
    }
  }

  class WorkerVolatile implements Runnable {
    volatile int count = 0;
    @Override
    public void run() {
      for (int i = 0; i < 1000_000; i++)
        count ++;
    }
  }

  class WorkerSynchronized implements Runnable {
    volatile int count = 0;
    @Override
    public void run() {
      synchronized (this) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  class WorkerSynchronizedNotVolatile implements Runnable {
    int count = 0;
    @Override
    public void run() {
      synchronized (this) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  class WorkerSynchronizedSomething implements Runnable {
    int count = 0;
    @Override
    public void run() {
      Object o = new Object();
      synchronized (o) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  public static void main(String[] args) throws Throwable {
    new ThreadExample3().goThreadSynchronized2();
  }

  void goThread() throws InterruptedException {
    Worker worker = new Worker();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThread: " + worker.count);
//    The count is not protected...
  }

  void goThreadVolatile() throws InterruptedException {
    WorkerVolatile worker = new WorkerVolatile();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThreadVolatile: " + worker.count);
//    Still the count is not protected...
  }

  void goThreadSynchronized() throws InterruptedException {
    WorkerSynchronized worker = new WorkerSynchronized();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThreadSynchronized: " + worker.count);
//    The count is correct!
  }

  void goThreadSynchronized2() throws InterruptedException {
    WorkerSynchronizedNotVolatile worker = new WorkerSynchronizedNotVolatile();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThreadSynchronized2: " + worker.count);
//    The count is correct! Volatile doesn't matter
  }

  void goThreadSynchronized3() throws InterruptedException {
    WorkerSynchronizedSomething worker = new WorkerSynchronizedSomething();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThreadSynchronized3: " + worker.count);
//    The lock is not on the right resource, so the count is still not protected...
  }
}
