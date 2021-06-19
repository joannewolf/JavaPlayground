package composition;

import superiterable.Student;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Tests {

    public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
        return s -> first.test(s) && second.test(s);
    }
    public static <E> Predicate<E> negate(Predicate<E> pred) {
        return s -> !pred.test(s);
    }
    public static Predicate<Student> getSmartPredicate(final double threshold) {
//        threshold += 0.5;
        return s -> s.getGpa() > threshold;
    }
    public static void main(String[] args) {
        Predicate<Student> smart = getSmartPredicate(3);
        Predicate<Student> verySmart = getSmartPredicate(3.7);
//        Predicate<Student> fairlySmart = s -> !(s.getGpa() > 3.7);
        Predicate<Student> fairlySmart = negate(verySmart);

        Stream.of(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Fred2", 2.5, "Math", "Physics"),
                new Student("Freddy", 3.5, "Math", "Physics"),
                new Student("Jim", 2.2, "Art History"),
                new Student("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
        )
//                .filter(smart)
//                .filter(fairlySmart)
                .filter(and(smart, negate(verySmart)))
                .forEach(System.out::println);
    }
}
