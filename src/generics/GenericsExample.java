package generics;

/**
 * Generics: <E extends A & B>
 */
interface Colored {
  String getColor();
}

interface Sized {
  int getSize();
}

class Pair<E> {
  private E left;
  private E right;

  public Pair(E left, E right) {
    this.left = left;
    this.right = right;
  }

  public E getLeft() { return left; }

  public E getRight() { return right; }

}

class ClothingPair<E extends Colored & Sized> extends Pair<E> {

  public ClothingPair(E left, E right) {
    super(left, right);
  }

  boolean matched() {
    return getLeft().getSize() == getRight().getSize() &&
      getLeft().getColor().equals(getRight().getColor());
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
  public int getSize() { return size; }

  @Override
  public String getColor() { return color; }
}

public class GenericsExample {

  public static void main(String[] args) {
    practiceGenerics();
  }

  static void practiceGenerics() {
    Pair<String> stringPair = new Pair<>("left!", "right!");
//    Pair<String> stringPair2 = new Pair("left!", 123);
    /*
      After remove <>, the compiler won't complain even the type of two arguments are different
      Because we didn't specify, it takes it as Unknown and hope the code is right, there will be warning
    */

    ClothingPair<Glove> unmatchedGlovePair = new ClothingPair<>(
      new Glove(1, "red"),
      new Glove(2, "blue")
    );
    System.out.println(unmatchedGlovePair.matched());

    ClothingPair<Glove> matchedGlovePair = new ClothingPair<>(
      new Glove(1, "red"),
      new Glove(1, "red")
    );
    System.out.println(matchedGlovePair.matched());
  }
}
