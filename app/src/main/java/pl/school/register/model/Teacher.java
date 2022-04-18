package pl.school.register.model;

import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.TEACHER)
public class Teacher extends Account {

    @OneToOne
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessons;

    @ManyToMany()
    @JoinTable(name = "teacher_subject",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "teacher")
    private Set<Mark> marks;

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Set<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Set<Lesson> lessons) {
        this.lessons = lessons;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
