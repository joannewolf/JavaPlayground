package concordance;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Target(ElementType.PARAMETER)
@interface NotNull {}
public class Concordance {
    private static final Pattern WORD_BOUDARY = Pattern.compile("\\W+");
    private static final Collector<String, ?, Map<String, Long>> buildMapOfWordCounts =
            Collectors.groupingBy(Function.identity(), Collectors.counting());
    public static void main(String[] args) throws Throwable {
        Comparator<Map.Entry<String, Long>> orderByValue = Map.Entry.comparingByValue();
        Comparator<Map.Entry<String, Long>> reverseOrderByValue = orderByValue.reversed();
        Files.lines(Paths.get("PrideAndPrejudice.txt"))
                .map(String::toLowerCase)
                .flatMap(WORD_BOUDARY::splitAsStream)
//                .filter(s -> s.length() > 0)
                .filter((@NotNull String s) -> s.length() > 0)
                .collect(buildMapOfWordCounts)
                .entrySet().stream()
//                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//                .sorted(reverseOrderByValue)
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(200)
                .map(e -> String.format("%20s : %5d", e.getKey(), e.getValue()))
                .forEach(System.out::println);
    }
}
