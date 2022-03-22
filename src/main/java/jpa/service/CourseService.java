package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

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

    public Course getCourseById(int id) {
        Course course = courseDao.getCourseById(id);
        if(course == null) {
            System.out.println("Unable to find course by id: " + id);
        } else {
            System.out.printf("%-20s %-20s %-20s\n", "Id", "Name", "Instructor");
            System.out.printf("%-20d %-20s %-20s\n", course.getCId(), course.getCName(), course.getCInstructor());
        }
        return course;
    }
}
