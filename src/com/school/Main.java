package com.school;

import java.util.ArrayList;
import java.util.List;

public class Main {
    
    // Method to demonstrate polymorphism
    public static void displaySchoolDirectory(List<Person> people) {
        System.out.println("\n=== School Directory (Polymorphic Display) ===");
        for (Person person : people) {
            person.displayDetails(); // Polymorphic call - actual method depends on runtime type
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Attendance Management System!");

        // Use ArrayLists now
        List<Student> students = new ArrayList<>();
        students.add(new Student("Chetan", "10th Grade"));
        students.add(new Student("Satya", "11th Grade"));
        students.add(new Student("Ravi", "12th Grade"));
        students.add(new Student("Anjali", "9th Grade"));

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("Mr. Sharma", "DBMS"));
        teachers.add(new Teacher("Ms. Priya", "OOPS"));

        List<Staff> staffMembers = new ArrayList<>();
        staffMembers.add(new Staff("Ramesh", "Lab Assistant"));
        staffMembers.add(new Staff("Suresh", "Clerk"));

        List<Course> courses = new ArrayList<>();
        courses.add(new Course("DBMS"));
        courses.add(new Course("OOPS"));
        courses.add(new Course("Computer Networks"));

        System.out.println("\n--- Student List ---");
        for (Student s : students) {
            s.displayDetails();
        }

        System.out.println("\n--- Teacher List ---");
        for (Teacher t : teachers) {
            t.displayDetails();
        }

        System.out.println("\n--- Staff List ---");
        for (Staff st : staffMembers) {
            st.displayDetails();
        }

        System.out.println("\n--- Course List ---");
        for (Course c : courses) {
            c.display();
        }

        // Create ArrayList of Person objects for polymorphism demonstration
        List<Person> schoolPeople = new ArrayList<>();
        schoolPeople.addAll(students);    // Add all students
        schoolPeople.addAll(teachers);    // Add all teachers  
        schoolPeople.addAll(staffMembers); // Add all staff members
        
        // Demonstrate polymorphism
        displaySchoolDirectory(schoolPeople);

        // Attendance Recording with updated constructor
        System.out.println("\n--- Attendance Log ---");
        List<AttendanceRecord> attendanceLog = new ArrayList<>();
        attendanceLog.add(new AttendanceRecord(students.get(0), courses.get(0), "Present"));
        attendanceLog.add(new AttendanceRecord(students.get(1), courses.get(1), "Absent"));
        attendanceLog.add(new AttendanceRecord(students.get(2), courses.get(2), "present"));
        attendanceLog.add(new AttendanceRecord(students.get(3), courses.get(1), "Late"));

        for (AttendanceRecord record : attendanceLog) {
            record.displayRecord();
        }

        // Save to files - Filter students from schoolPeople for file storage
        FileStorageService storage = new FileStorageService();
        
        // Filter only Student instances from schoolPeople list for file storage
        List<Student> studentsForStorage = new ArrayList<>();
        for (Person person : schoolPeople) {
            if (person instanceof Student) {
                studentsForStorage.add((Student) person);
            }
        }
        
        storage.saveData(studentsForStorage, "students.txt");
        storage.saveData(courses, "courses.txt");
        storage.saveData(attendanceLog, "attendance_log.txt");
    }
}