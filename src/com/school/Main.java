package com.school;

public class Main {
    public static void main(String[] args) {
        Student[] students = {
            new Student("Lokesh", 101),
            new Student("Bhavdeep", 102),
            new Student("Eswar", 103)
        };

        Course[] courses = {
            new Course("DBMS", "MTH101"),
            new Course("OOPS", "PHY101")
        };

        System.out.println("Student Details:");
        for (Student s : students) {
            s.display();
        }

        System.out.println("\nCourse Details:");
        for (Course c : courses) {
            c.display();
        }
    }
}