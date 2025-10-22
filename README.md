# Attendance Management Project

This is a Java project for the Attendance Management System.

## Author

M Lokeswara Reddy

## Squad

S86

---

## Part 1

* Initial setup with folder structure and Welcome app.

## Part 2

* Added `Student` and `Course` classes.
* Updated `Main.java` to use arrays of objects.
* Displayed student and course details.

## Part 3

* Implemented **auto ID generation** for Students and Courses:

  * Student IDs start from **S1, S2, …**
  * Course IDs start from **C101, C102, …**
* Removed `setDetails()` methods and replaced with constructors.
* Updated `Main.java` to demonstrate multiple Student and Course objects with auto-generated IDs.

## Part 4

* Applied **Encapsulation** with private fields and getters.
* Added new `AttendanceRecord` class with validation.
* Demonstrated **basic attendance recording** with valid and invalid statuses.
* Updated `Main.java` to show attendance log.

## Part 5

* Implemented **inheritance hierarchy**:

  * Created `Person` base class with auto-increment ID and `name`.
  * `Student` extends `Person` with `gradeLevel`.
  * `Teacher` extends `Person` with `subjectTaught`.
  * `Staff` extends `Person` with `role`.
* Updated `Main.java` to:

  * Create objects of `Student`, `Teacher`, `Staff`.
  * Call `displayDetails()` on each object.
  * Use `student.getId()` in `AttendanceRecord`.
* Demonstrated unified ID generation and role-based display.

## Part 6

* Implemented **Interface-Driven Persistence** with storage:
  * Created `Storable` interface with `toDataString()` method.
  * Implemented `FileStorageService` class for saving data to files.
  * Made `Student`, `Course`, and `AttendanceRecord` implement `Storable`.
  * Updated `Main.java` to save data to text files.

## Part 7

* Demonstrated **Runtime Polymorphism** using Person hierarchy:
  * Modified `AttendanceRecord` to use `Student` and `Course` objects instead of IDs.
  * Updated constructor to accept `Student` and `Course` objects.
  * Enhanced `displayRecord()` to show student names and course names.
  * Added `displaySchoolDirectory(List<Person>)` method to demonstrate polymorphic calls.
  * Created unified `schoolPeople` list containing all Person objects.
  * Implemented filtering using `instanceof` for file storage.
* **Key Features:**
  * Polymorphic method calls in School Directory display.
  * Richer attendance log with names instead of just IDs.
  * Proper object-oriented design with composition in AttendanceRecord.

---

## How to Run

1. Navigate to project root.
2. Compile all Java files:

   ```bash
   javac -d . src/com/school/*.java
   ```
3. Run the program:

   ```bash
   java com.school.Main
   ```

## Expected Output

The program will display:
1. Individual lists of Students, Teachers, and Staff
2. Course list
3. **School Directory** - Demonstrating polymorphism where each Person object calls its specific `displayDetails()` method
4. **Attendance Log** - Showing enhanced records with student names and course names
5. File save confirmations for persistence