package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Generics: try customized Comparator, and implement own thenComparing() & reversed()
 */
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
public class StreamExample4 {

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


  }
}
