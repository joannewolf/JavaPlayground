package thread;

/**
 * Even synchronization is not on the shared resource, it might work too.
 */
public class ThreadExample4 {
  private int count = 0;
  private Object key = new Object();

  class Worker implements Runnable {
    @Override
    public void run() {
      synchronized (key) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  class Worker2 implements Runnable {
    @Override
    public void run() {
      synchronized (key) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  public static void main(String[] args) throws Throwable {
    new ThreadExample4().goThread();
  }

  void goThread() throws InterruptedException {
    Thread thread1 = new Thread(new Worker());
    Thread thread2 = new Thread(new Worker2());

    thread1.start();
    thread2.start();
    Thread.sleep(1000);
    System.out.println("In goThread: " + count);
//    The count is correct! Because we lock on the same Object, even thought it's not the shared resource
  }
}
