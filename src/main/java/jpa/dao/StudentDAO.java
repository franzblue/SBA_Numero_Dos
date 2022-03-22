package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.entitymodels.StudentCourses;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public interface StudentDAO {

    static final String PERSISTENCE_UNIT_NAME = "db";

    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public default List<Student> getAllStudents() {
        EntityManager em = emFactoryObj.createEntityManager();

        // SELECT * FROM Student
        String sql = "SELECT s FROM Student s";
        TypedQuery<Student> query = em.createQuery(sql, Student.class);

        try {
            return query.getResultList();
        } catch(NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

    public default Student getStudentByEmail(String email) {
        EntityManager em = emFactoryObj.createEntityManager();

        // SELECT * FROM Student WHERE id =
        String sql = "SELECT s FROM Student s WHERE s.sEmail = :sEmail";
        TypedQuery<Student> query = em.createQuery(sql, Student.class);
        query.setParameter("sEmail", email);

        try {
            return query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }

    public default boolean validateStudent(String email, String password) {
        Scanner reader = new Scanner(System.in);
        boolean b = false;
        while (b == false) {
            Student s = this.getStudentByEmail(email);
            if (s == null) {
                System.out.println("Provided email does not match any records.");
                System.out.println("Please enter an email.");
                email = reader.nextLine();
            } else if (!s.getSPass().equals(password)) {
                System.out.println("Wrong password provided.");
                System.out.println("Enter your password.");
                password = reader.nextLine();
            } else if (s.getSEmail().equals(email) && s.getSPass().equals(password)) {
                System.out.println("Welcome " + s.getSName());
                b = true;
            }
        } return b;
    }

    public default void registerStudentToCourse(StudentCourses courses) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();

        // save the course to the database
        em.merge(courses);

        // commit the transaction
        em.getTransaction().commit();
        em.clear();
    }

    public default List<StudentCourses> getStudentCourses(String email) {
        EntityManager em = emFactoryObj.createEntityManager();


        String sql = "SELECT sc FROM StudentCourses sc WHERE sc.eMail = :sEmail";
        TypedQuery<StudentCourses> query = em.createQuery(sql, StudentCourses.class);
        query.setParameter("sEmail", email);

        try {
            return query.getResultList();
        } catch(NoResultException e) {
            return null;
        }
    }
}
