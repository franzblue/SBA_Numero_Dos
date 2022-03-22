package jpa.dao;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import javax.persistence.*;
import java.util.List;

public interface CourseDAO {

    static final String PERSISTENCE_UNIT_NAME = "db";

    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public default List<Course> getAllCourses() {
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT c FROM Course c";
        TypedQuery<Course> query = em.createQuery(sql, Course.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public default Course getCourseById(int id) {
        EntityManager em = emFactoryObj.createEntityManager();

        // SELECT * FROM Student WHERE id =
        String sql = "SELECT c FROM Course c WHERE c.cId = :cId";
        TypedQuery<Course> query = em.createQuery(sql, Course.class);
        query.setParameter("cId", id);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
