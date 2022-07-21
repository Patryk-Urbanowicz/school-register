package pl.school.register.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue(Role.Values.ROLE_STUDENT)
@Data
public class Student extends Account {

    @ManyToMany(mappedBy = "children")
    @EqualsAndHashCode.Exclude
    private Set<Parent> parents;

    @ManyToOne
    @NotNull
    private SchoolClass schoolClass;

    @OneToMany(mappedBy = "student")
    @EqualsAndHashCode.Exclude
    private Set<Attendance> attendances = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    @EqualsAndHashCode.Exclude
    private Set<Mark> marks = new HashSet<>();

    private Long Index;

    public Student() {
        this.setRole(Role.ROLE_STUDENT);
    }
}
