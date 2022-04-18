package pl.school.register.model;

import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.STUDENT)
public class Student extends Account {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;

    @ManyToOne
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances;

    @OneToMany(mappedBy = "student")
    private Set<Mark> marks;

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public Set<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(Set<Attendance> attendances) {
        this.attendances = attendances;
    }

    public Set<Mark> getMarks() {
        return marks;
    }

    public void setMarks(Set<Mark> marks) {
        this.marks = marks;
    }
}
