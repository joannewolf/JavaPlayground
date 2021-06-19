package superiterable;

import java.util.Arrays;

public class School {
    public static void main(String[] args) {
        SuperIterable<Student> school = new SuperIterable<>(Arrays.asList(
                new Student("Fred", 3.2, "Math", "Physics"),
                new Student("Jim", 2.2, "Art History"),
                new Student("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
        ));
        System.out.println("--------------------");
        school
                .filter(s -> s.getGpa() > 3)
                .map(s -> s.getName())
                .forEach(s -> System.out.println(s));
        System.out.println("--------------------");
        school
                .flatMap(s -> new SuperIterable<>(s.getCourses()))
                .forEach(s -> System.out.println(s));
    }
}
