import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {
  private static final Pattern WORD_BOUNDARY = Pattern.compile("\\W+");

  public static void main(String[] args) {
    Path path = Paths.get("/Users/cfu4/Downloads/pride_and_prejudice.txt");
    try {
      Comparator<Entry<String, Long>> orderByValue = Map.Entry.comparingByValue();
      Comparator<Entry<String, Long>> reverseOrderByValue = orderByValue.reversed();

      Comparator<Entry<String, Long>> orderByValueDesc = Map.Entry.<String, Long>comparingByValue().reversed();

      Files.lines(path)
        .flatMap(line -> WORD_BOUNDARY.splitAsStream(line))
        .filter(word -> word.length() > 0)
//          .collect(Collectors.groupingBy(word -> word, Collectors.counting()))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
//        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
        .sorted(orderByValueDesc)
        .limit(200)
        .forEach(entry -> System.out.println(entry.getKey() + " -> " + entry.getValue()));

      Map<String, Long> wordMap = Files.lines(path)
        .map(line -> line.split(" "))
        .flatMap(words -> Arrays.stream(words))
        .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
//      System.out.println(wordMap);

      Map<String, Long> sortedWordMap = Files.lines(path)
          .map(line -> line.split(" "))
          .flatMap(words -> Arrays.stream(words))
          .collect(Collectors.groupingBy(word -> word,
              () -> new TreeMap<>(
                new Comparator<String>() {
                  @Override
                  public int compare(String o1, String o2) {
                    return (o2.compareTo(o1));
                  }
                }),
              Collectors.counting()));
//      System.out.println(sortedWordMap);
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
