package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Stream: concordance of "Pride and Prejudice"
 */
public class StreamExample3 {

  public static void main(String[] args) {
    final Pattern WORD_BOUDARY = Pattern.compile("\\W+");
    Path path = Paths.get("PrideAndPrejudice.txt");
    try {
      List<String> words = Files.lines(path)
        .map(String::toLowerCase)
        .flatMap(WORD_BOUDARY::splitAsStream)
        .filter(s -> s.length() > 0)
        .collect(Collectors.toList());

//    Concordance of Pride and Prejudice:
      words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
//        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//        .sorted(reverseOrderByValue)
        .sorted(Entry.<String, Long>comparingByValue().reversed())
        .limit(200)
        .map(e -> String.format("%s: %d", e.getKey(), e.getValue()))
        .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

//    1) Build & print a table of word-length frequency
//    2) Build & print a table of frequency of counts-of-distinct-letters in the words. I.e. the word "hello" has four distinct letters, while letters has five.
//    3) Build a table of letter frequency for the book & print in descending order.
//    5) Calculate the ratio of "distinct letters to actual letters" in the books words.
  }
}
