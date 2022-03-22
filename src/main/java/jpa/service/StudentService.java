package jpa.service;

import jpa.dao.CourseDAO;
import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import java.util.List;
import java.util.Scanner;

public class StudentService {

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

    public List<Student> getAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        if(students == null) {
            System.out.println("An error occurred while retrieving data.");
        } return students;
    }

    public Student getStudentByEmail(String email) {
        Student student = studentDAO.getStudentByEmail(email);
        if(student == null) {
            System.out.println("Unable to find student by email: " + email);
        } else {
            System.out.printf("%-20s %-20s %-20s\n", "Email", "Name", "Password");
            System.out.printf("%-20s %-20s %-20s\n", student.getSEmail(), student.getSName(), student.getSPass());
        }
        return student;
    }

    public boolean validateStudent(String email, String password) {
        return studentDAO.validateStudent(email, password);
    }

    public List<StudentCourses> getStudentCourses(String email) {
        List<StudentCourses> courses = studentDAO.getStudentCourses(email);
        return courses;
    }

    public void registerStudentToCourse(String email, int cId) {
        CourseDAO courseDAO = new CourseDAO() {
            @Override
            public Course getCourseById(int cId) {
                return CourseDAO.super.getCourseById(cId);
            }
        };
        StudentCourses courses = new StudentCourses((int) Math.random(), email, courseDAO.getCourseById(cId).getCName(), courseDAO.getCourseById(cId).getCInstructor(), cId);
        studentDAO.registerStudentToCourse(courses);
    }
}
