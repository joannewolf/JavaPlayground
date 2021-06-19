package parallel;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Average {
    private long count;
    private double sum;

    public void include(double d) {
        sum += d;
        count++;
    }

    public void merge(Average other) {
        this.count += other.count;
        this.sum += other.sum;
    }
    public Optional<Double> get() {
        if (count > 0) return Optional.of(sum / count);
        else return Optional.empty();
    }
}
public class Averaging {
    public static void main(String[] args) {
        long start = System.nanoTime();
        ThreadLocalRandom.current().doubles(1_200_000_000L, -Math.PI, +Math.PI)
                .parallel()
                .map(Math::sin)
                .collect(Average::new, Average::include, Average::merge)
                .get()
                .ifPresent(System.out::println);
        long totalTime = System.nanoTime() - start;
        System.out.printf("Time taken %7.3f\n", (totalTime / 1_000_000_000.0));
    }
}
