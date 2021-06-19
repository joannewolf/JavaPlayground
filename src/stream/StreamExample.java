package stream;

import java.util.Arrays;
import java.util.List;

/**
 * Stream of customized class array.
 */
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
//      Fred takes Math
//      Fred takes Physics
//      Jim takes Art History
//      Sheila takes Math
//     ... etc...
    students.stream()
      .flatMap(student -> student.getCourses().stream()
        .map(course -> String.format("%s takes %s", student.getName(), course))
      )
      .forEach(System.out::println);
  }
}
