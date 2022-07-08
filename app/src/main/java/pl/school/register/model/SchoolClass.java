package pl.school.register.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String className;
    private String profile;

    @OneToOne
    private Teacher homeroomTeacher;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "schoolClass")
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(mappedBy = "schoolClass")
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "schoolClass")
    private Set<Student_Class> studentClasses = new HashSet<>();

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

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
