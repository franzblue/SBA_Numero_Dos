package jpa.mainrunner;

import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;
import jpa.service.CourseService;
import jpa.service.StudentCourseService;
import jpa.service.StudentService;

public class SMSRunner {

    private Scanner sin;
    private StringBuilder sb;

    private CourseService courseService;
    private StudentService studentService;
    private Student currentStudent;

    public SMSRunner() {
        sin = new Scanner(System.in);
        sb = new StringBuilder();
        courseService = new CourseService();
        studentService = new StudentService();
    }

    public static void main(String[] args) {

        SMSRunner sms = new SMSRunner();
        sms.run();
    }

    private void run() {
        // Login or quit
        switch (menu1()) {
            case 1:
                if (studentLogin()) {
                    registerMenu();
                }
                break;
            case 2:
                out.println("Goodbye!");
                break;

            default:

        }
    }

    private int menu1() {
        sb.append("\n1.Student Login\n2. Quit Application\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());

        return sin.nextInt();
    }

    private boolean studentLogin() {
        boolean retValue = false;
        out.print("Enter your email address: ");
        String email = sin.next();
        out.print("Enter your password: ");
        String password = sin.next();

        Student students = studentService.getStudentByEmail(email);
        if (students != null) {
            currentStudent = students;
        }

        if (currentStudent != null & currentStudent.getSPass().equals(password)) {
            List<StudentCourses> courses = studentService.getStudentCourses(email);
            out.println("MyClasses");
            for (StudentCourses course : courses) {
                out.printf("%-5s%-15S%-15s\n", course.getCourseID(), course.getcName(), course.getInstructor());
            }
            retValue = true;
        } else {
            out.println("User Validation failed. GoodBye!");
        }
        return retValue;
    }

    private void registerMenu() {
        sb.append("\n1.Register a class\n2. Logout\nPlease Enter Selection: ");
        out.print(sb.toString());
        sb.delete(0, sb.length());

        switch (sin.nextInt()) {
            case 1:
                List<Course> allCourses = courseService.getAllCourses();
                List<StudentCourses> studentCourses = studentService.getStudentCourses(currentStudent.getSEmail());
                allCourses.removeAll(studentCourses);
                out.printf("%5s%15S%15s\n", "ID", "Course", "Instructor");
                for (Course course : allCourses) {
                    out.printf("%-5s%-20S%-20s\n", course.getCId(), course.getCName(), course.getCInstructor());
                }
                out.println();
                out.print("Enter Course Number: ");
                int number = sin.nextInt();
                int newCourse = courseService.getCourseById(number).getCId();

                if (newCourse > 0) {
                    studentService.registerStudentToCourse(currentStudent.getSEmail(), newCourse);
                    Student temp = studentService.getStudentByEmail(currentStudent.getSEmail());

                    StudentCourseService scService = new StudentCourseService();
                    List<StudentCourses> sCourses = scService.getAllStudentCourses(temp.getSEmail());


                    out.println("MyClasses");
                    for (StudentCourses course : sCourses) {
                        out.printf("%-5s%-15S%-15s\n", course.getCourseID(), course.getcName(), course.getInstructor());
                    }
                }
                break;
            case 2:
            default:
                out.println("Goodbye!");
        }
    }
}
