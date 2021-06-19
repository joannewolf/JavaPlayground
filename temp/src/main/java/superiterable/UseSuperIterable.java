package superiterable;

import java.util.Arrays;
import java.util.function.Function;

//interface Operation<E, F> {
//    F apply(E s);
//}

class StringLength implements Function<String, Integer> {

    @Override
    public Integer apply(String s) {
        return s.length();
    }
}

class UpperCaseStringOp implements Function<String, String> {
    public String apply(String s) {
        return s.toUpperCase();
    }
}

public class UseSuperIterable {
    public static void main(String[] args) {
        SuperIterable<String> sis =
                new SuperIterable<>(Arrays.asList("Fred", "Jim", "Sheila"));
        sis.forEach(s -> System.out.println("> " + s));

        sis
                .filter(s -> s.length() > 3)
                .map(new UpperCaseStringOp())
                .forEach(s -> System.out.println(">> " + s));

//        SuperIterable<Integer> sisl = sis.map(new StringLength());
//
//        for (Integer s : sisl) {
//            System.out.println("> " + s);
//        }
        sis
                .map(s -> s.length())
                .forEach(s -> System.out.println("> " + s));
    }
}
