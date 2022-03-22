package jpa.service;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import java.util.List;

public class StudentCourseService {

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

    public List<StudentCourses> getAllStudentCourses(String email) {
        return studentDAO.getStudentCourses(email);
    }
}
