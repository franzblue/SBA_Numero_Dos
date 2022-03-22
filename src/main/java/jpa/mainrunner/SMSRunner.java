package jpa.mainrunner;

import jpa.service.CourseService;
import jpa.service.StudentService;

public class SMSRunner {

    public static void main(String[] args) {
        new CourseService().getAllCourses();
        new StudentService().getAllStudents();
        new StudentService().getStudentByEmail("hi");
        new StudentService().validateStudent("hi", "test");
//        new StudentService().registerStudentToCourse("hi", 1);
//        new StudentService().getStudentCourses("hi");
    }
}
