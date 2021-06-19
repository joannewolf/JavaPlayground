package cocontravariance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Ex1 {
    public static <E> List<E> filter(Iterable<E> in, Predicate<? super E> pred) {
        List<E> res = new ArrayList<>();

        in.forEach(e -> {
            if (pred.test(e)) res.add(e);
        });
        return res;
    }
    public static void main(String[] args) {
        List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");
//        Predicate<String> longStrings = s -> s.length() > 3;
        Predicate<CharSequence> longStrings = s -> s.length() > 3;

        System.out.println("string is long? " + longStrings.test("this is long"));

        System.out.println(filter(ls, longStrings));

        List<StringBuilder> lsb = Arrays.asList(new StringBuilder("Fred"),
                new StringBuilder("Jim"), new StringBuilder("Sheila"));
//        Predicate<StringBuilder> longStringBuilders = s -> s.length() > 3;

//        System.out.println(filter(lsb, longStringBuilders));
        System.out.println(filter(lsb, longStrings));
    }
}
