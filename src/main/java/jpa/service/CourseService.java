package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

import java.util.List;

public class CourseService {

    private CourseDAO courseDao = new CourseDAO() {
        @Override
        public List<Course> getAllCourses() {
            return CourseDAO.super.getAllCourses();
        }
    };

    public List<Course> getAllCourses() {
        List<Course> courses = courseDao.getAllCourses();
        if(courses == null) {
            System.out.println("An error occurred while retrieving data.");
        } else {
            System.out.printf("%-5s %-30s %-20s\n", "ID", "COURSE NAME", "INSTRUCTOR NAME");
            for (Course course : courses) {
                System.out.printf("%-5d %-30s %-20s\n", course.getCId(), course.getCName(), course.getCInstructor());
            }
        } return courses;
    }
}
