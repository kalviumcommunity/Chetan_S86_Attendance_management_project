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
        registrationService.createCourse("Mathematics", 3);
        registrationService.createCourse("Physics", 2);
        registrationService.createCourse("Chemistry", 4);
        registrationService.createCourse("Biology", 2);

        // Display school directory
        System.out.println("School Directory:\n");
        displaySchoolDirectory(registrationService);

        // Display course details
        System.out.println("\nCourse Details:");
        for (Course c : registrationService.getCourses()) {
            c.display();
        }

        // Enroll students in courses
        System.out.println("\n=== Enrolling Students in Courses ===");
        List<Student> students = registrationService.getStudents();
        List<Course> courses = registrationService.getCourses();
        
        if (students.size() >= 5 && courses.size() >= 4) {
            // Enroll students in Mathematics (capacity: 3)
            registrationService.enrollStudentInCourse(students.get(0), courses.get(0)); // Alice -> Mathematics
            registrationService.enrollStudentInCourse(students.get(1), courses.get(0)); // Bob -> Mathematics
            registrationService.enrollStudentInCourse(students.get(2), courses.get(0)); // Charlie -> Mathematics
            
            // Try to enroll one more student in Mathematics (should fail - exceeds capacity)
            registrationService.enrollStudentInCourse(students.get(3), courses.get(0)); // David -> Mathematics (FAIL)
            
            // Enroll students in Physics (capacity: 2)
            registrationService.enrollStudentInCourse(students.get(1), courses.get(1)); // Bob -> Physics
            registrationService.enrollStudentInCourse(students.get(4), courses.get(1)); // Eva -> Physics
            
            // Try to enroll one more student in Physics (should fail - exceeds capacity)
            registrationService.enrollStudentInCourse(students.get(0), courses.get(1)); // Alice -> Physics (FAIL)
            
            // Enroll students in Chemistry (capacity: 4)
            registrationService.enrollStudentInCourse(students.get(2), courses.get(2)); // Charlie -> Chemistry
            registrationService.enrollStudentInCourse(students.get(3), courses.get(2)); // David -> Chemistry
            
            // Enroll student in Biology (capacity: 2)
            registrationService.enrollStudentInCourse(students.get(4), courses.get(3)); // Eva -> Biology
        }

        // Display updated course details with enrollment information
        System.out.println("\n=== Updated Course Details (After Enrollment) ===");
        for (Course c : courses) {
            c.display();
            System.out.println();
        }

        // Mark attendance using IDs (which will use registrationService internally)
        System.out.println("=== Marking Attendance ===");
        // Mark attendance using IDs (which will use registrationService internally)
        System.out.println("=== Marking Attendance ===");
        
        if (students.size() >= 4 && courses.size() >= 3) {
            // Optional: Check if student is enrolled before marking attendance
            Course mathCourse = courses.get(0);
            if (mathCourse.getEnrolledStudents().contains(students.get(0))) {
                attendanceService.markAttendance(students.get(0).getId(), mathCourse.getCourseId(), "Present");
            }
            if (mathCourse.getEnrolledStudents().contains(students.get(1))) {
                attendanceService.markAttendance(students.get(1).getId(), mathCourse.getCourseId(), "Present");
            }
            
            Course physicsCourse = courses.get(1);
            if (physicsCourse.getEnrolledStudents().contains(students.get(1))) {
                attendanceService.markAttendance(students.get(1).getId(), physicsCourse.getCourseId(), "Absent");
            }
            
            Course chemistryCourse = courses.get(2);
            if (chemistryCourse.getEnrolledStudents().contains(students.get(2))) {
                attendanceService.markAttendance(students.get(2).getId(), chemistryCourse.getCourseId(), "Late");
            }
            if (chemistryCourse.getEnrolledStudents().contains(students.get(3))) {
                attendanceService.markAttendance(students.get(3).getId(), chemistryCourse.getCourseId(), "Present");
            }
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