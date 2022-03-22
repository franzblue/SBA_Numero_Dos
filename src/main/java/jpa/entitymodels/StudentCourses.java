package jpa.entitymodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table( name="courses")
@IdClass( StudentCoursesID.class)
@NamedQueries({
        @NamedQuery( name="CoursesByStudent", query="Select c from StudentCourses c where c.eMail = :email")
})
public class StudentCourses {
    @Id
    @Column(name="student_email")
    private String eMail;

    public StudentCourses() {}

    public StudentCourses(String eMail, int courseID) {
        this.eMail = eMail;
        this.courseID = courseID;
    }

    @Id
    @Column(name="course_id")
    private int courseID;

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + courseID;
        result = prime * result + ((eMail == null) ? 0 : eMail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourses other = (StudentCourses) obj;
        if (courseID != other.courseID)
            return false;
        if (eMail == null) {
            if (other.eMail != null)
                return false;
        } else if (!eMail.equals(other.eMail))
            return false;
        return true;
    }

}
