package queues;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class QueueWithReentrantLock<E> {
    private static final int MAX_SIZE = 10;
    private E[] data = (E[])new Object[MAX_SIZE];
    private int count = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition NOT_FULL = lock.newCondition();
    private final Condition NOT_EMPTY = lock.newCondition();

    public QueueWithReentrantLock(){}

    public void put(E item) throws InterruptedException {
        lock.lock();
        try {
            while (count == MAX_SIZE)
                NOT_FULL.await();
            data[count++] = item;
            NOT_EMPTY.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                NOT_EMPTY.await();
            E res = data[0];
            System.arraycopy(data, 1, data, 0, --count);
            NOT_FULL.signal();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Throwable {
        final QueueWithReentrantLock<int[]> queue = new QueueWithReentrantLock<>();

        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    int[] data = {0, i};
                    if (i < 100) Thread.sleep(100);
                    if (i == 500) data[1] = 0;
                    data[0] = i;
                    queue.put(data); data = null;
                } catch (InterruptedException ie) {
                    System.out.println("Interrupted???");
                }
            }
            System.out.println("Producer finished...");
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    int[] data = queue.take();
                    if (i > 900) Thread.sleep(100);
                    if (data[0] != data[1] || data[0] != i) System.out.println("ERROR at index " + i);
                } catch (InterruptedException ie) {
                    System.out.println("Interrupted???");
                }
            }
            System.out.println("Consumer finished...");
        }).start();
    }
}
