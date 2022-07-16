package pl.school.register.model;

import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.TEACHER)
public class Teacher extends Account {

    @OneToOne(mappedBy = "homeroomTeacher")
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "teacher")
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private Set<Mark> marks = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "teacher_subject",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    Set<Meeting> meetings = new HashSet<>();

    public Teacher() {
        this.setRole(Role.TEACHER);
    }

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

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }
}
