package jpa.entitymodels;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "instructor")
    private String cInstructor;

    public Course() {
    }

    public Course(int id, String name, String instructor) {
        this.cId = id;
        this.cName = name;
        this.cInstructor = instructor;
    }
}
