package pl.school.register.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.PARENT)
public class Parent extends Account {

    @ManyToMany
    @JoinTable(name = "parent_student",
    joinColumns = @JoinColumn(name = "parent_id"),
    inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> children;

    public Set<Student> getChildren() {
        return children;
    }

    public void setChildren(Set<Student> children) {
        this.children = children;
    }
}
