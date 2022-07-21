package pl.school.register.model;

import lombok.Data;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.PARENT)
@Data
public class Parent extends Account {

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "parent_student",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> children = new HashSet<>();

    public Parent() {
        this.setRole(Role.PARENT);
    }
}
