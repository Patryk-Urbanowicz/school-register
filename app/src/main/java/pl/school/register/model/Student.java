package pl.school.register.model;

import lombok.Data;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.STUDENT)
@Data
public class Student extends Account {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;

    @ManyToOne
    @NotNull
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private Set<Mark> marks = new HashSet<>();

    private Long Index;

    public Student() {
        this.setRole(Role.STUDENT);
    }
}
