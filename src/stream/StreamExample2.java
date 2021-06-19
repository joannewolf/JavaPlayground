package stream;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Entirely with streams, no loops, no use of "substring".
 * Simulate throwing ten dice at a time. Add up the face value (i.e. a result in the range 10-60).
 * Repeat this 10,000 times, collecting the count of how many times each face-total shows up.
 * Create a bar chart of the frequencies, something like:
 * 10: *
 *  ...
 * 28: **********************************
 * 29: *************************************
 * 30: **************************************
 *  ...
 */
public class StreamExample2 {
  public static void main(String[] args) {
    final int SAMPLE_SIZE = 10000;
    IntStream.range(1, SAMPLE_SIZE)
      .map(round -> ThreadLocalRandom.current().ints(10, 1, 7).sum()).boxed()
      .collect(Collectors.groupingBy(sum -> sum, Collectors.counting())).entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .map(entry -> String.format("%d: %s", entry.getKey(), new String(new char[entry.getValue().intValue()]).replace("\0", "*")))
      .forEach(System.out::println);
  }
}
