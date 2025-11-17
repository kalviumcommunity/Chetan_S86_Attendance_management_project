package com.school;

import java.util.List;

public class Main {
    public static void displaySchoolDirectory(RegistrationService regService) {
        List<Person> people = regService.getAllPeople();
        for (Person person : people) {
            person.displayDetails();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Initialize services
        FileStorageService storage = new FileStorageService();
        RegistrationService registrationService = new RegistrationService(storage);
        AttendanceService attendanceService = new AttendanceService(storage, registrationService);

        // Register students using RegistrationService
        registrationService.registerStudent("Alice", "Grade 10");
        registrationService.registerStudent("Bob", "Grade 11");
        registrationService.registerStudent("Charlie", "Grade 12");
        registrationService.registerStudent("David", "Grade 10");
        registrationService.registerStudent("Eva", "Grade 11");

        // Register teachers using RegistrationService
        registrationService.registerTeacher("Mr. Smith", "Mathematics");
        registrationService.registerTeacher("Ms. Johnson", "Physics");

        // Register staff using RegistrationService
        registrationService.registerStaff("Mrs. Brown", "Administrator");
        registrationService.registerStaff("Mr. Green", "Janitor");

        // Create courses using RegistrationService
        registrationService.createCourse("Mathematics");
        registrationService.createCourse("Physics");
        registrationService.createCourse("Chemistry");
        registrationService.createCourse("Biology");

        // Display school directory
        System.out.println("School Directory:\n");
        displaySchoolDirectory(registrationService);

        // Display course details
        System.out.println("Course Details:");
        for (Course c : registrationService.getCourses()) {
            c.display();
        }

        // Mark attendance using IDs (which will use registrationService internally)
        List<Student> students = registrationService.getStudents();
        List<Course> courses = registrationService.getCourses();
        
        if (students.size() >= 4 && courses.size() >= 3) {
            attendanceService.markAttendance(students.get(0).getId(), courses.get(0).getCourseId(), "Present");
            attendanceService.markAttendance(students.get(1).getId(), courses.get(1).getCourseId(), "Absent");
            attendanceService.markAttendance(students.get(2).getId(), courses.get(2).getCourseId(), "Late");
            attendanceService.markAttendance(students.get(3).getId(), courses.get(0).getCourseId(), "Present");
        }

        System.out.println("\nAll Attendance Records (via attendanceService):");
        attendanceService.displayAttendanceLog();

        if (students.size() > 0) {
            System.out.println("\nAttendance Records for Student: " + students.get(0).getName());
            attendanceService.displayAttendanceLog(students.get(0));
        }

        if (courses.size() > 0) {
            System.out.println("\nAttendance Records for Course: " + courses.get(0).getCourseName());
            attendanceService.displayAttendanceLog(courses.get(0));
        }

        // Save all data using the services
        registrationService.saveAllRegistrations();
        attendanceService.saveAttendanceData();
    }
}