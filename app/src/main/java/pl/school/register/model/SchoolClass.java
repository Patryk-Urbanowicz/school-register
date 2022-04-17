package pl.school.register.model;

import javax.persistence.*;

@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String profile;

    @OneToOne
    private Teacher homeroomTeacher;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Teacher getHomeroomTeacher() {
        return homeroomTeacher;
    }

    public void setHomeroomTeacher(Teacher homeroomTeacher) {
        this.homeroomTeacher = homeroomTeacher;
    }
}
