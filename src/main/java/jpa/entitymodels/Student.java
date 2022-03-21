package jpa.entitymodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name ="Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email")
    private String sEmail;

    @Column(name = "name")
    private String sName;

    @Column(name = "password")
    private String sPass;

    // map to "Course" table
//    private List sCourses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sEmail.equals(student.sEmail) && sName.equals(student.sName) && sPass.equals(student.sPass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sEmail, sName, sPass);
    }
}
