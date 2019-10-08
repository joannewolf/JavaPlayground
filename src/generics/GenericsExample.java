package generics;

interface Colored {
  String getColor();
}

interface Sized {
  int getSize();
}

class Pair<E extends Sized & Colored> {
  private E left;
  private E right;

  public Pair(E left, E right) {
    this.left = left;
    this.right = right;
  }

  public E getLeft() { return left; }

  public E getRight() { return right; }

  boolean matched() {
    return (left.getSize() == right.getSize()) && (left.getColor().equals(right.getColor()));
  }
}

class Glove implements Colored, Sized {
  private int size;
  private String color;

  public Glove(int size, String color) {
    this.size = size;
    this.color = color;
  }

  @Override
  public int getSize() {
    return size;
  }

  @Override
  public String getColor() {
    return color;
  }
}

public class GenericsExample {

  public static void main(String[] args) {
    practiceGenerics();
  }

  static void practiceGenerics() {
//    Pair<String> stringPair = new Pair<>("left!", "right!");
    Pair<Glove> unmatchedGlovePair = new Pair<>(
      new Glove(1, "red"),
      new Glove(2, "blue")
    );
    System.out.println(unmatchedGlovePair.matched());

    Pair<Glove> matchedGlovePair = new Pair<>(
      new Glove(1, "red"),
      new Glove(1, "red")
    );
    System.out.println(matchedGlovePair.matched());
  }
}
