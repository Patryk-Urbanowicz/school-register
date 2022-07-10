package pl.school.register.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Teacher teacher;

    @ManyToOne
    @NotNull
    private SchoolClass schoolClass;

    @ManyToOne
    @NotNull
    private Subject subject;

    @OneToMany(mappedBy = "lesson")
    private Set<LessonBlock> lessonBlocks = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<Meeting> meetings = new HashSet<>();

    @OneToMany(mappedBy = "lesson")
    private Set<Mark> marks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Set<LessonBlock> getLessonBlocks() {
        return lessonBlocks;
    }

    public void setLessonBlocks(Set<LessonBlock> lessonBlocks) {
        this.lessonBlocks = lessonBlocks;
    }

    public Set<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Set<Meeting> meetings) {
        this.meetings = meetings;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
