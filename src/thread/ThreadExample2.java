package thread;

/**
 * Compiler might be too smart to move your command order / do some cache
 * So your change outside the thread is not effective, try volatile!
 */
public class ThreadExample2 {
  private boolean bool = true;
  private volatile boolean boolVolatile = true;

  class Worker implements Runnable {
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + ": " + bool);
      System.out.println("Going to infinite loop...");
      while (bool) {}
//      It never goes out this while loop even if main thread change the bool to false...
      System.out.println("Leave infinite loop...");
    }
  }

  class WorkerVolatile implements Runnable {
    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + ": " + bool);
      System.out.println("Going to infinite loop...");
      while (boolVolatile) {}
//      It can see the change made by other thread
      System.out.println("Leave infinite loop...");
    }
  }

  public static void main(String[] args) throws Throwable {
    new ThreadExample2().goThreadVolatile();
  }

  void goThread() throws InterruptedException {
    Worker worker = new Worker();
    Thread thread = new Thread(worker);

    thread.start();
    Thread.sleep(1000);
    bool = false;
    System.out.println(Thread.currentThread().getName() + ": " + bool);
  }

  void goThreadVolatile() throws InterruptedException {
    WorkerVolatile worker = new WorkerVolatile();
    Thread thread = new Thread(worker);

    thread.start();
    Thread.sleep(1000);
    boolVolatile = false;
    System.out.println(Thread.currentThread().getName() + ": " + boolVolatile);
  }
}
