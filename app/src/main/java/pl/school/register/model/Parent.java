package pl.school.register.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.ROLE_PARENT)
@Data
public class Parent extends Account {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parent_student",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    @EqualsAndHashCode.Exclude
    private Set<Student> children = new HashSet<>();

    public Parent() {
        this.setRole(Role.ROLE_PARENT);
    }
}
