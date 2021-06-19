package composition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Pair<E> {
    private E left;
    private E right;

    public Pair(E left, E right) {
        this.left = left;
        this.right = right;
    }

    public E getLeft() {
        return left;
    }

    public E getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "left=" + left +
                ", right=" + right +
                '}';
    }

    public static <E> Comparator<Pair<E>> compareLeft(Comparator<E> comp) {
        return (p1, p2) -> comp.compare(p1.left, p2.left);
    }

    public static <E> Comparator<Pair<E>> compareRight(Comparator<E> comp) {
        return (p1, p2) -> comp.compare(p1.right, p2.right);
    }
}

public class ComparatorChaining {

    public static <E> Comparator<E> thenCompare(Comparator<E> first, Comparator<E> second) {
        return (e1, e2) -> {
            int firstTry = first.compare(e1, e2);
            if (firstTry == 0) {
                return second.compare(e1, e2);
            } else return firstTry;
        };
    }

    public static void main(String[] args) {
        List<Pair<String>> lps = new ArrayList<>(Arrays.asList(
                new Pair<>("Freda", "Smith"),
                new Pair<>("Bill", "Smith"),
                new Pair<>("Sarah", "Smith"),
                new Pair<>("Susan", "Jones"),
                new Pair<>("Alan", "Jones"),
                new Pair<>("Willy", "Jones"),
                new Pair<>("Fred", "Smith")
        ));

        System.out.println(("------------------------"));
        lps.sort(Pair.compareRight(String::compareTo));
        lps.forEach(System.out::println);

        System.out.println(("------------------------"));
        lps.sort(Pair.compareLeft(String::compareTo));
        lps.forEach(System.out::println);

        System.out.println(("------------------------"));
//        lps.sort(thenCompare(Pair.compareRight(String::compareTo), Pair.compareLeft(String::compareTo)));
        lps.sort(Pair.compareRight(String::compareTo).thenComparing(Pair.compareLeft(String::compareTo)).reversed());
        lps.forEach(System.out::println);
    }
}
