package jpa.mainrunner;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;

public class SMSRunner {

    private StudentDAO studentDAO = new StudentDAO() {
        @Override
        public Student getAllStudents() {
            return StudentDAO.super.getAllStudents();
        }

        @Override
        public Student getStudentByEmail(String email) {
            return StudentDAO.super.getStudentByEmail(email);
        }
    };

    public void testing() {
        getAllStudents();
        getStudentByEmail();
    }

    public void getAllStudents() {
        Student student = studentDAO.getAllStudents();
        if(student == null) {
            System.out.println("An error occurred while retrieving data.");
        } else {
            System.out.println("Here are all students: " + student.toString());
        }
    }

    public void getStudentByEmail() {
        String email = "ljiroudek8@sitemeter.com";
        Student student = studentDAO.getStudentByEmail(email);
        if(student == null) {
            System.out.println("Unable to find student by email: " + email);
        } else {
            System.out.println("Found with email: " + student.toString());
        }
    }
}
