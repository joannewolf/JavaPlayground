package streamlab;

import superiterable.Student;

import java.util.Arrays;
import java.util.List;

public class Students {
    public static void main(String[] args) {
        List<Student> ls = Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Alice", 3.7, "Math", "Physics", "Quantum Mechanics"),
                new Student("Freda", 3.0, "Math", "Statistics"),
                new Student("Susan", 3.4, "Geography", "Political Science", "History"),
                new Student("William", 2.3, "Political Science"),
                new Student("Jim", 2.2, "Art History"),
                new Student("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
        );
        System.out.println("1) All student names");
        ls.stream()
                .map(Student::getName)
                .forEach(System.out::println);
        System.out.println("2) All students shown with grade");
        ls.stream()
                .map(s -> s.getName() + " has grade " + s.getGpa())
                .forEach(System.out::println);
        System.out.println("3) All \"smart\" students with grade and course count");
        ls.stream()
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName() + " has grade " + s.getGpa() + " and takes " + s.getCourses().size() + " courses")
                .forEach(System.out::println);
        System.out.println("4) All the courses taken by all students (include duplicates)");
        ls.stream()
                .flatMap(s -> s.getCourses().stream())
                .forEach(System.out::println);
        System.out.println("5) All courses, exclude duplicates");
        ls.stream()
                .flatMap(s -> s.getCourses().stream())
                .distinct()
                .forEach(System.out::println);
        System.out.println("6) All courses, in alphabetical order");
        ls.stream()
                .flatMap(s -> s.getCourses().stream())
                .distinct()
                .sorted()
                .forEach(System.out::println);
        System.out.println("7) Show all student/course combinations");
        ls.stream()
                .flatMap(s -> s.getCourses().stream().map(c -> s.getName() + " takes " + c))
                .forEach(System.out::println);
    }
}
