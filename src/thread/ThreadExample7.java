//package thread;
//
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * ReentrantLock
// */
//class MyQueueWithLock<E> {
//  private static final int MAX_SIZE = 10;
//  private int count = 0;
//  private E[] data = (E[])new Object[MAX_SIZE];
//
//  private final ReentrantLock lock = new ReentrantLock();
//  private Condition notFull = lock.newCondition();
//  private Condition notEmpty = lock.newCondition();
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
//public class ThreadExample7 {
//  final MyQueueWithLock<int[]> queue = new MyQueueWithLock<>();
//
//  public static void main(String[] args) {
//
//    new Thread(() -> {
//      for (int i = 0; i < 1000; i++) {
//        try {
//          int[] data = {0, i};
//          if (i < 100) Thread.sleep(100);
//          if (i == 500) data[1] = 0;
//          data[0] = i;
//          queue.put(data);
//        } catch (InterruptedException ie) {
//          System.out.println("Interrupted???");
//        }
//      }
//      System.out.println("Producer finished...");
//    }).start();
//    new Thread(() -> {
//      for (int i = 0; i < 1000; i++) {
//        try {
//          int[] data = queue.take();
//          if (i > 900) Thread.sleep(100);
//          if (data[0] != data[1] || data[0] != i) System.out.println("ERROR at index " + i);
//        } catch (InterruptedException ie) {
//          System.out.println("Interrupted???");
//        }
//      }
//      System.out.println("Consumer finished...");
//    }).start();
//  }
//}
