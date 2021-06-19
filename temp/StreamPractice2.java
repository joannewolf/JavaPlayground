import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

class Student {
  private String firstName;
  private String lastName;

  public Student(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  @Override
  public String toString() {
    return "Student{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        '}';
  }
}

public class StreamPractice2 {

  static Comparator<Student> compareByFirstName = (s1, s2) -> s1.getFirstName().compareTo(s2.getFirstName());
  static Comparator<Student> compareByLastName = (s1, s2) -> s1.getLastName().compareTo(s2.getLastName());
  static Comparator<Student> compareByField(String fieldName) throws NoSuchFieldException, IllegalAccessException {
    Field f = Student.class.getDeclaredField(fieldName);
    Comparator<Student> res = (s1, s2) -> ((String) f.get(s1)).compareTo((String) f.get(s2));
    return res;
  };

//  static Comparator<Student> compareInOrder(Comparator<Student> first, Comparator<Student> second) {
  static <E> Comparator<E> compareInOrder(Comparator<E> first, Comparator<E> second) {
    return (s1, s2) -> first.compare(s1, s2) != 0 ? first.compare(s1, s2) : second.compare(s1, s2);
  }
//  static Comparator<Student> reverse(Comparator<Student> comp) {
  static <E> Comparator<E> reverse(Comparator<E> comp) {
    return (s1, s2) -> -comp.compare(s1, s2);
  }

  public static void main(String[] args) {
    Student s1 = new Student("AA", "AAA");
    Student s2 = new Student("AA", "AAB");
    Student s3 = new Student("AA", "AAC");
    Student s4 = new Student("AB", "AAA");
    Student s5 = new Student("BB", "AAA");

    List<Student> studentList = Arrays.asList(s1, s2, s3, s4, s5);

    studentList.stream()
//      .sorted(compareByFirstName)
//      .sorted(compareByLastName)
      .sorted(compareInOrder(compareByFirstName, reverse(compareByLastName)))
//      .sorted(compareInOrder(compareByLastName, compareByFirstName))
//      .sorted(reverse(compareByFirstName))
//      .sorted(compareByFirstName.thenComparing(compareByLastName.reversed()))
      .forEach(s -> System.out.println(s));
  }
}
