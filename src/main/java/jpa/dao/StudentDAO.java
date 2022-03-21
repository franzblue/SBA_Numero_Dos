package jpa.dao;

import jpa.entitymodels.Student;

import javax.persistence.*;

public interface StudentDAO {

    static final String PERSISTENCE_UNIT_NAME = "db";

    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

//    getAllStudents();

    public default Student getAllStudents() {
        EntityManager em = emFactoryObj.createEntityManager();

        // SELECT * FROM Student
        String sql = "SELECT s FROM Student s";
        TypedQuery<Student> query = em.createQuery(sql, Student.class);

        try {
            return (Student) query.getResultStream();
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
//    validateStudent();
//
//    registerStudentToCourse();
//
//    getStudentCourses();
}
