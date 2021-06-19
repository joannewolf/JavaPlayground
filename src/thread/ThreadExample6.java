//package thread;
//
//import java.util.Arrays;
//
///**
// * synchronized + wait + notify
// * Will fail when multiple producer & consumer?? in case multiple producer full up the queue and multiple consumer take up th queue
// * And both will be locked
// */
//class MyQueue<E> {
//  private static final int MAX_SIZE = 10;
//  private int count = 0;
//  private E[] data = (E[])new Object[MAX_SIZE];
//
//  public int getCount() { return count; }
//
//  public int getMaxSize() { return MAX_SIZE; }
//
//  void put(E e) throws InterruptedException {
//    synchronized (this) {
//      while (count == MAX_SIZE)
//        this.wait();
//      this.notify();
//      data[count] = e;
//      count++;
//    }
//  }
//
//  E take() throws InterruptedException {
//    synchronized (this) {
//      while (count == 0)
//        this.wait();
//      this.notify();
//      E res = data[0];
//      System.arraycopy(data, 1, data, 0, --count);
//      return res;
//    }
//  }
//}
//
//public class ThreadExample6 {
//  class WorkerProducer implements Runnable {
//    @Override
//    public void run() {
//      try {
//        for (int i = 0; i < myQueue.getMaxSize(); i++) {
//          myQueue.put(new int[] {i, i});
//          Thread.sleep(100);
//        }
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  class WorkerConsumer implements Runnable {
//    @Override
//    public void run() {
//      try {
//        for (int i = 0; i < myQueue.getMaxSize(); i++) {
//          int[] res = myQueue.take();
//          System.out.println(res[0] + ", " + res[1]);
//        }
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//
//  public static void main(String[] args) throws Throwable {
//    new ThreadExample6().goTestQueue();
////    new ThreadExample6().goThread();
//  }
//
//  void goTestQueue() throws Throwable {
//    MyQueue<Integer> myQueue = new MyQueue<>();
//    myQueue.put(1);
//    myQueue.put(2);
//    myQueue.put(3);
//    myQueue.put(4);
//    myQueue.put(5);
//    myQueue.put(6);
//    myQueue.put(7);
//    myQueue.put(8);
//    myQueue.put(9);
//    myQueue.put(10);
////    myQueue.put(11);
//
//    System.out.println(myQueue.take());
//    System.out.println(myQueue.take());
//    System.out.println(myQueue.take());
////    myQueue.show();
//  }
//
//  void goThread() throws Throwable {
//    final MyQueue<int []> myQueue = new MyQueue();
//
//    WorkerProducer workerProducer = new WorkerProducer();
//    WorkerConsumer workerConsumer = new WorkerConsumer();
//    Thread thread1 = new Thread(workerProducer);
//    Thread thread2 = new Thread(workerConsumer);
//
//    thread1.start();
//    thread2.start();
////    System.out.println(myQueue.pop()[0]);
//  }
//}
