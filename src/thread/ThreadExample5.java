package thread;

/**
 * It's not guaranteed that when we print the result, the threads are finished, try join!
 */
public class ThreadExample5 {
  class Worker implements Runnable {
    int count = 0;
    @Override
    public void run() {
      synchronized (this) {
        for (int i = 0; i < 1000_000; i++)
          count ++;
      }
    }
  }

  public static void main(String[] args) throws Throwable {
    new ThreadExample5().goThread();
  }

  void goThread() throws InterruptedException {
    Worker worker = new Worker();
    Thread thread1 = new Thread(worker);
    Thread thread2 = new Thread(worker);

    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println("In goThread: " + worker.count);
//    The count is correct! Because we make sure both threads are finished.
  }
}
