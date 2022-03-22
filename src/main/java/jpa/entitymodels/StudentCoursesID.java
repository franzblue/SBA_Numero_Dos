package jpa.entitymodels;

import java.io.Serializable;

import javax.persistence.Column;

public class StudentCoursesID implements Serializable {

    private static final long serialVersionUID = 1L;

    private String eMail;
    private int courseID;

    public StudentCoursesID() {
    }

    public StudentCoursesID(String email, String courseId) {
        this.seteMail(email);
        this.setCourseID(courseID);
    }

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
        StudentCoursesID other = (StudentCoursesID) obj;
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
