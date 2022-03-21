package jpa.mainrunner;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public class SMSRunner {

    private StudentDAO studentDAO = new StudentDAO() {
        @Override
        public List<Student> getAllStudents() {
            return StudentDAO.super.getAllStudents();
        }

        @Override
        public Student getStudentByEmail(String email) {
            return StudentDAO.super.getStudentByEmail(email);
        }
    };

    private CourseDAO courseDao = new CourseDAO() {
        @Override
        public List<Course> getAllCourses() {
            return CourseDAO.super.getAllCourses();
        }
    };

    public void testing() {
        getAllStudents();
        System.out.println("\n");
        getStudentByEmail();
        System.out.println("\n");
        getAllCourses();

    }

    public void getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if(students == null) {
            System.out.println("An error occurred while retrieving data.");
        } else {
            System.out.printf("%-30s %-20s %-20s\n", "Email", "Name", "Password");
            for (Student student : students) {
                System.out.printf("%-30s %-20s %-20s\n", student.getEmail(), student.getName(), student.getPassword());
            }
        }
    }

    public void getStudentByEmail() {
        String email = "ljiroudek8@sitemeter.com";
        Student student = studentDAO.getStudentByEmail(email);
        if(student == null) {
            System.out.println("Unable to find student by email: " + email);
        } else {
            System.out.printf("%-20s %-20s %-20s\n", "Email", "Name", "Password");
            System.out.printf("%-20s %-20s %-20s", student.getEmail(), student.getName(), student.getPassword());
        }
    }

    public void getAllCourses() {
        List<Course> courses = courseDao.getAllCourses();
        if(courses == null) {
            System.out.println("An error occurred while retrieving data.");
        } else {
            System.out.printf("%-5s %-30s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
            for (Course course : courses) {
                System.out.printf("%-5d %-30s %-20s\n", course.getId(), course.getName(), course.getInstructor());
            }
        }
    }

    public static void main(String[] args) {
        new SMSRunner().testing();
    }
}
