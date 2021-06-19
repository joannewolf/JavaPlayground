package parallel;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class MisCounting {
    public static void main(String[] args) {
//        AtomicInteger store = new AtomicInteger();
        int [] store = {0};
//        int store = 0;
        long start = System.nanoTime();
        long count = Stream.generate(() -> 1)
                .limit(1_000_000_000)
//                .parallel()
//                .peek(x -> store.addAndGet(x))
                .peek(x -> store[0] += x)
//                .peek(x -> store += x)
                .count();
        System.out.println("Total stored value is " + store[0]
//        System.out.println("Total stored value is " + store.get()
        + " items in stream number " + count);
        long time = System.nanoTime() - start;
        System.out.printf("Time was %7.3f\n", (time / 1_000_000_000.0));
    }
}
