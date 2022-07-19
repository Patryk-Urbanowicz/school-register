package pl.school.register.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.TEACHER)
@Data
public class Teacher extends Account {

    @OneToOne(mappedBy = "homeroomTeacher")
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "teacher")
    @EqualsAndHashCode.Exclude
    private Set<Lesson> lessons = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "teacher")
    private Set<Mark> marks = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "teacher_subject",
    joinColumns = @JoinColumn(name = "teacher_id"),
    inverseJoinColumns = @JoinColumn(name = "subject_id"))
    @EqualsAndHashCode.Exclude
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "teacher")
    @EqualsAndHashCode.Exclude
    Set<Meeting> meetings = new HashSet<>();

    public Teacher() {
        this.setRole(Role.TEACHER);
    }
}
