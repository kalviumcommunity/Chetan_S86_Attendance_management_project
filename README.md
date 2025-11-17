# Attendance Management Project

This is a Java-based project developed as part of Kalvium's Object Oriented Programming course (SPE 2025). The goal of this project is to build an attendance system using OOP principles.

## Getting Started

To run this project:

1. Compile the code: `javac src\com\school\Main.java`
2. Run the code: `java -cp src com.school.Main`


## Part 2 - Core Domain Modelling

- Added `Student` and `Course` classes with constructors and display methods.
- Created arrays of students and courses.
- Printed their details using `Main.java`.


## Part 3: Constructor Initialization & Auto-ID Generation
- Implemented parameterized constructors in `Student` and `Course` classes for object initialization.
- Utilized `private static` member variables for automatic and unique ID generation.
- Demonstrated the use of the `this` keyword to distinguish instance variables from constructor parameters.
- Changed `Course`'s `courseId` to `int` for simpler auto-generation and updated its display.
- Updated `Main.java` to use constructors and show ID progression.

### How to Run (ensure this is up-to-date)
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/Student.java src/com/school/Course.java src/com/school/Main.java` (or `javac src/com/school/*.java`)
3. Run: `java -cp src com.school.Main`

## Part 4: Data Encapsulation & Attendance Recording Validation
- Applied encapsulation to `Student` and `Course` classes by making fields `private` and adding public `getters`.
- Introduced a new `AttendanceRecord` class with `private` fields, a constructor, and `getters` to store attendance data.
- Implemented basic validation in the `AttendanceRecord` constructor for the attendance status (allowing only "Present" or "Absent").
- Used an `ArrayList` in `Main.java` to store and display `AttendanceRecord` objects.
- Demonstrated retrieving IDs using getters (e.g., `student1.getStudentId()`) when creating records.

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/*.java` (or list individual files including `AttendanceRecord.java`)
3. Run: `java -cp src com.school.Main`

## Part 5: Establishing Students, Teaching & Non-Teaching Staff hierarchy
- Created a base class `Person.java` with common attributes (`id`, `name`), a universal auto-ID generator, and a `displayDetails()` method.
- Modified `Student.java` to inherit from `Person`, using `super()` to call the parent constructor and overriding `displayDetails()` to add student-specific info (e.g., grade level).
- Created `Teacher.java` extending `Person`, adding a `subjectTaught` attribute and its own `displayDetails()`.
- Created `Staff.java` extending `Person`, adding a `role` attribute and its own `displayDetails()`.
- Demonstrated creation and display of `Student`, `Teacher`, and `Staff` objects in `Main.java`.
- Updated `AttendanceRecord` creation to use the inherited `getId()` method.

## Part 6: Interface-Driven Persistence with Storage
- Defined a `Storable` interface with a `toDataString()` method.
- Modified `Student`, `Course`, and `AttendanceRecord` classes to implement the `Storable` interface and provide their specific `toDataString()` implementations (CSV format).
- Created a `FileStorageService` class with a `saveData(List<? extends Storable> items, String filename)` method to write `Storable` objects to a text file.
- Utilized `try-with-resources` for safe file handling (`PrintWriter`, `FileWriter`).
- Demonstrated in `Main.java` how to save lists of students, courses, and attendance records to separate files (`students.txt`, `courses.txt`, `attendance_log.txt`).
- Discussed the flexibility provided by interfaces for handling different types of storable objects uniformly.

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/*.java`
3. Run: `java -cp src com.school.Main`
4. Check the generated files: `students.txt`, `courses.txt`, `attendance_log.txt`.

## Part 7: Polymorphic Behaviour in Attendance and Displaying Reports
- Modified `AttendanceRecord` to hold `Student` and `Course` objects instead of just their IDs, enhancing its object-oriented nature and how records are displayed. The `toDataString()` method still uses IDs for simpler file storage.
- Created a `displaySchoolDirectory(List<Person> people)` method in `Main.java` to demonstrate polymorphism. This method iterates through a list of `Person` objects (containing `Student`, `Teacher`, `Staff` instances) and calls `person.displayDetails()`. The correct overridden method for each specific object type is executed at runtime.
- Populated a `List<Person>` in `main` and used it with `displaySchoolDirectory`.
- Updated `Main.java` to use `instanceof` and casting when preparing the list of students for saving, as `Person` itself does not implement `Storable`.
- Discussed how polymorphism allows for flexible and extensible code by treating different object types uniformly through a common interface or base class reference.

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/*.java`
3. Run: `java -cp src com.school.Main`

## Part 8: Overloaded Commands: Multiple Ways to Mark and Query Attendance
- Created an `AttendanceService.java` class to encapsulate attendance logic and manage the list of `AttendanceRecord` objects.
- Implemented overloaded `markAttendance` methods in `AttendanceService`:
    - `markAttendance(Student student, Course course, String status)`
    - `markAttendance(int studentId, int courseId, String status, List<Student> allStudents, List<Course> allCourses)` (performs lookups)
- Implemented overloaded `displayAttendanceLog` methods in `AttendanceService`:
    - `displayAttendanceLog()` (shows all records)
    - `displayAttendanceLog(Student student)` (filters by student)
    - `displayAttendanceLog(Course course)` (filters by course)
- Utilized Java Streams for filtering records in the specific display methods.
- `AttendanceService` now uses `FileStorageService` to save its `attendanceLog`.
- Demonstrated the use of these overloaded methods in `Main.java`, showing how different method signatures allow for flexible ways to call the same conceptual operation.

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac src/com/school/*.java`
3. Run: `java -cp src com.school.Main`
4. Check `attendance_log.txt` for saved records.

## Part 9: RegistrationService and Dependency Injection

### Overview
In Part 9, we introduced a centralized `RegistrationService` to manage all entity registrations (students, teachers, staff, and courses) and refactored the application to use dependency injection patterns.

### Key Changes

#### 1. Teacher and Staff Classes
- Both `Teacher.java` and `Staff.java` now implement the `Storable` interface
- Added `toDataString()` method to serialize their data in CSV format (id, name, specific attribute)
- This enables saving teacher and staff information to files

#### 2. RegistrationService (New Class)
A central service that manages all entity registrations and provides:
- **Registration methods:**
  - `registerStudent(String name, String gradeLevel)`
  - `registerTeacher(String name, String subjectTaught)`
  - `registerStaff(String name, String role)`
  - `createCourse(String courseName)`
- **Getter methods:**
  - `getStudents()`, `getTeachers()`, `getStaffMembers()`, `getCourses()`
- **Lookup methods:**
  - `findStudentById(int id)` - finds a student by their ID
  - `findCourseById(int id)` - finds a course by its ID
- **Polymorphic method:**
  - `getAllPeople()` - returns a unified `List<Person>` containing all students, teachers, and staff
- **Persistence method:**
  - `saveAllRegistrations()` - saves all managed entities to their respective files (students.txt, teachers.txt, staff.txt, courses.txt)

#### 3. AttendanceService Refactoring
- Added `RegistrationService` as a dependency (constructor injection)
- Updated `markAttendance(int studentId, int courseId, String status)` to use `registrationService` for student and course lookups
- Removed private helper methods (`findStudentById`, `findCourseById`) as lookups are now delegated to `RegistrationService`
- Simplified the attendance marking process by centralizing entity management

#### 4. Main.java Refactoring
- Instantiated services in proper dependency order:
  1. `FileStorageService` (no dependencies)
  2. `RegistrationService` (depends on FileStorageService)
  3. `AttendanceService` (depends on both FileStorageService and RegistrationService)
- Replaced direct object creation with registration service methods
- Updated `displaySchoolDirectory()` to accept `RegistrationService` and use `getAllPeople()`
- Simplified attendance marking by using the ID-based method that leverages `RegistrationService`
- Consolidated data persistence with `registrationService.saveAllRegistrations()` and `attendanceService.saveAttendanceData()`

### Generated Files
Running the application now generates five data files:
- `students.txt` - Contains student records (id, name, gradeLevel)
- `teachers.txt` - Contains teacher records (id, name, subjectTaught)
- `staff.txt` - Contains staff records (id, name, role)
- `courses.txt` - Contains course records (courseId, courseName)
- `attendance_log.txt` - Contains attendance records

### Design Benefits
- **Separation of Concerns:** Each service has a clear, single responsibility
- **Dependency Injection:** Services receive their dependencies through constructors, making the code more testable and maintainable
- **Centralized Management:** All entity registration and lookup logic is centralized in `RegistrationService`
- **Reduced Coupling:** Classes depend on abstractions rather than concrete implementations

### How to Run
1. Navigate to the project root directory.
2. Compile: `javac --release 17 src/com/school/*.java` (or `javac src/com/school/*.java` if using Java 17 runtime)
3. Run: `java -cp src com.school.Main`
4. Verify the generated files: `students.txt`, `teachers.txt`, `staff.txt`, `courses.txt`, and `attendance_log.txt`
