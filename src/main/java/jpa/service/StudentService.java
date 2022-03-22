package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;

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
        } else {
            System.out.printf("%-30s %-20s %-20s\n", "Email", "Name", "Password");
            for (Student student : students) {
                System.out.printf("%-30s %-20s %-20s\n", student.getSEmail(), student.getSName(), student.getSPass());
            }
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

//    public void registerStudentToCourse(String email, int cId) {
//
//    }
}
