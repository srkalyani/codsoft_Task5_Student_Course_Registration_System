import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    String code;
    String title;
    String description;
    int capacity;
    String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }
}

class Student {
    int studentID;
    String name;
    List<String> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }
}

class CourseRegistrationSystem {
    List<Course> courses;
    List<Student> students;

    public CourseRegistrationSystem() {
        this.courses = new ArrayList<>();
        this.students = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Course Code: " + course.code);
            System.out.println("Title: " + course.title);
            System.out.println("Description: " + course.description);
            System.out.println("Capacity: " + course.capacity);
            System.out.println("Schedule: " + course.schedule);
            System.out.println("----------------------------");
        }
    }

    public void registerStudent(Student student, String courseCode) {
        for (Course course : courses) {
            if (course.code.equals(courseCode) && course.capacity > 0) {
                student.registeredCourses.add(courseCode);
                course.capacity--;
                System.out.println("Registration successful for course: " + courseCode);
                return;
            }
        }
        System.out.println("Course not found or no available slots.");
    }

    public void dropCourse(Student student, String courseCode) {
        if (student.registeredCourses.contains(courseCode)) {
            student.registeredCourses.remove(courseCode);
            for (Course course : courses) {
                if (course.code.equals(courseCode)) {
                    course.capacity++;
                    System.out.println("Course dropped successfully: " + courseCode);
                    return;
                }
            }
        } else {
            System.out.println("Student not registered for course: " + courseCode);
        }
    }
}

public class task5 {
    public static void main(String[] args) {
        CourseRegistrationSystem registrationSystem = new CourseRegistrationSystem();
        Scanner scanner = new Scanner(System.in);

        // Adding some sample courses
        registrationSystem.addCourse(new Course("CS101", "Introduction to Programming", "Basic programming concepts", 30, "Mon/Wed 10:00 AM"));
        registrationSystem.addCourse(new Course("MA201", "Probability", "conceptsof Probability", 25, "Tue/Thu 2:00 PM"));

        // Adding some sample students
        Student student1 = new Student(1, "Shraddha Kalyani");
        Student student2 = new Student(2, "Samiksha Mule");

        // Displaying available courses
        registrationSystem.displayCourses();

        // Registering students for courses
        System.out.print("Enter student ID for registration: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter course code for registration: ");
        String courseCode = scanner.next();
        registrationSystem.registerStudent(new Student(studentID, ""), courseCode);

        // Displaying available courses after registration
        registrationSystem.displayCourses();

        // Dropping a course for a student
        System.out.print("Enter student ID for dropping a course: ");
        studentID = scanner.nextInt();
        System.out.print("Enter course code for dropping a course: ");
        courseCode = scanner.next();
        registrationSystem.dropCourse(new Student(studentID, ""), courseCode);

        // Displaying available courses after dropping
        registrationSystem.displayCourses();
        scanner.close();
    }
}