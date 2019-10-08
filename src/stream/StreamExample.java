package stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Student {
  private String name;
  private double gpa;
  private List<String> courses;

  public Student(String name, double gpa, String ... courses) {
    this.name = name;
    this.gpa = gpa;
    this.courses = Arrays.asList(courses);
  }

  public String getName() {
    return name;
  }

  public double getGpa() {
    return gpa;
  }

  public List<String> getCourses() {
    return courses;
  }

  @Override
  public String toString() {
    return "Student{" +
      "name='" + name + '\'' +
      ", gpa=" + gpa +
      ", courses=" + courses +
      '}';
  }
}

public class StreamExample {

  public static void main(String[] args) {
      practiceBasicStream3();
  }

  static void practiceBasicStream() {
    List<Student> students = Arrays.asList(
      new Student("Fred", 3.2, "Math", "Physics"),
      new Student("Alice", 3.7, "Math", "Physics", "Quantum Mechanics"),
      new Student("Freda", 3.0, "Math", "Statistics"),
      new Student("Susan", 3.4, "Geography", "Political Science", "History"),
      new Student("William", 2.3, "Political Science"),
      new Student("Jim", 2.2, "Art History"),
      new Student("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

//    1) All student names
//    students.stream()
//      .map(student -> student.getName())
//      .forEach(System.out::println);

//    2) All students shown with grade, in the form: Fred has grade 3.2
//    students.stream()
//      .map(student -> String.format("%s has grade %f", student.getName(), student.getGpa()))
//      .forEach(System.out::println);

//    3) All "smart" students with grade and course count, e.g.: Fred has grade 3.2 and takes 3 classes
//    students.stream()
//      .filter(student -> student.getGpa() > 3.2)
//      .map(student -> String.format("%s has grade %f takes %d classes", student.getName(), student.getGpa(), student.getCourses().size()))
//      .forEach(System.out::println);

//    4) All the courses taken by all students (include duplicates)
//    students.stream()
//      .flatMap(student -> student.getCourses().stream())
//      .forEach(System.out::println);

//    5) Use the API docs to modify 4 to exclude duplicates
//    students.stream()
//      .flatMap(student -> student.getCourses().stream())
//      .distinct()
//      .forEach(System.out::println);

//    6) Use the API docs to modify 5 to show in alphabetical order
//    students.stream()
//      .flatMap(student -> student.getCourses().stream())
//      .distinct()
//      .sorted()
//      .forEach(System.out::println);

//    7) Show all student/course combinations, e.g.:
//    Fred takes Math
//    Fred takes Physics
//    Jim takes Art History
//    Sheila takes Math
//   ... etc...
//    students.stream()
//      .flatMap(student -> student.getCourses().stream()
//        .map(course -> String.format("%s takes %s", student.getName(), course))
//      )
//      .forEach(System.out::println);
  }

  static void practiceBasicStream2() {
    /*
      Entirely with streams, no loops, no use of "substring".
      Simulate throwing ten dice at a time. Add up the face value (i.e. a result in the range 10-60).
      Repeat this 10,000 times, collecting the count of how many times each face-total shows up.
      Create a bar chart of the frequencies, something like:
      10: *
      [...]
      28: **********************************
      29: *************************************
      30: **************************************
      ...
     */
    final int SAMPLE_SIZE = 10000;
    IntStream.range(1, SAMPLE_SIZE)
      .map(round -> ThreadLocalRandom.current().ints(10, 1, 7).sum()).boxed()
      .collect(Collectors.groupingBy(sum -> sum, Collectors.counting())).entrySet().stream()
      .sorted(Map.Entry.comparingByKey())
      .map(entry -> String.format("%d: %s", entry.getKey(), new String(new char[entry.getValue().intValue()]).replace("\0", "*")))
      .forEach(System.out::println);


  }

  static void practiceBasicStream3() {
    final Pattern WORD_BOUDARY = Pattern.compile("\\W+");
    Path path = Paths.get("PrideAndPrejudice.txt");
    try {
      List<String> words = Files.lines(path)
        .map(String::toLowerCase)
        .flatMap(WORD_BOUDARY::splitAsStream)
        .filter(s -> s.length() > 0)
        .collect(Collectors.toList());

//    Concordance of Pride and Prejudice:
      words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
//        .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
//        .sorted(reverseOrderByValue)
        .sorted(Entry.<String, Long>comparingByValue().reversed())
        .limit(200)
        .map(e -> String.format("%s: %d", e.getKey(), e.getValue()))
        .forEach(System.out::println);
    } catch (IOException e) {
      e.printStackTrace();
    }

//    1) Build & print a table of word-length frequency
//    2) Build & print a table of frequency of counts-of-distinct-letters in the words. I.e. the word "hello" has four distinct letters, while letters has five.
//    3) Build a table of letter frequency for the book & print in descending order.
//    5) Calculate the ratio of "distinct letters to actual letters" in the books words.
  }
}
