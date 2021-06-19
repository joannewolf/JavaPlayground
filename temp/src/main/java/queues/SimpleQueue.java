package queues;

public class SimpleQueue<E> {
    private static final int MAX_SIZE = 10;
    private E[] data = (E[])new Object[MAX_SIZE];
    private int count = 0;

    public SimpleQueue(){}

    public void put(E item) throws InterruptedException {
        synchronized(this) {
            while (count == MAX_SIZE)
                this.wait();
//            this.notify();
            this.notifyAll();
            data[count++] = item;
        }
    }

    public E take() throws InterruptedException {
        synchronized (this) {
            while (count == 0)
                this.wait();
//            this.notify();
            this.notifyAll();
            E res = data[0];
            System.arraycopy(data, 1, data, 0, --count);
            return res;
        }
    }

    public static void main(String[] args) throws Throwable {
        final SimpleQueue<int[]> queue = new SimpleQueue<>();

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
//    public static void main(String[] args) throws Throwable {
//        SimpleQueue<String> sqs = new SimpleQueue<>();
//        sqs.put("Fred");
//        sqs.put("Jim");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
//        sqs.put("Sheila");
////        sqs.put("Sheila");
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
//        System.out.println("take gives: " + sqs.take());
////        System.out.println("take gives: " + sqs.take());
//    }

}
