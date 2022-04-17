package pl.school.register.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@DiscriminatorValue(Role.Values.STUDENT)
public class Student extends Account {

    @ManyToMany(mappedBy = "children")
    private Set<Parent> parents;

    public Set<Parent> getParents() {
        return parents;
    }

    public void setParents(Set<Parent> parents) {
        this.parents = parents;
    }
}
