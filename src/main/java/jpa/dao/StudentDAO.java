package jpa.dao;

import jpa.entitymodels.Student;

import javax.persistence.*;
import java.util.List;

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
        String sql = "SELECT s FROM Student s WHERE s.email = :sEmail";
        TypedQuery<Student> query = em.createQuery(sql, Student.class);
        query.setParameter("sEmail", email);

        try {
            return query.getSingleResult();
        } catch(NoResultException e) {
            System.out.println(e);
            return null;
        }
    }

//    public default Student validateStudent(String email, String name, String password) {
//        Student s = this.getStudentByEmail(email);
//        if(s.getEmail() == email && s.getName() == name && s.getPassword() == password) {
//            System.out.println("Welcome " + name);
//        } else if(s.getPassword() != password) {
//            System.out.println("Wrong password.");
//        }
//        return s;
//    }

//    registerStudentToCourse();
//
//    getStudentCourses();
}
