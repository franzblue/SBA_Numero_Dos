package jpa.dao;

import jpa.entitymodels.Course;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public interface CourseDAO {

    static final String PERSISTENCE_UNIT_NAME = "db";

    EntityManagerFactory emFactoryObj =
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public default Course getAllCourses() {
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT c FROM Course";
        TypedQuery<Course> query = em.createQuery(sql, Course.class);

        try {
            return (Course) query.getResultStream();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
