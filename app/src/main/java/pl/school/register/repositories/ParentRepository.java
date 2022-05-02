package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Parent;

public interface ParentRepository extends CrudRepository<Parent, Long> {
}
