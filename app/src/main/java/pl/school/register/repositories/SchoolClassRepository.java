package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.SchoolClass;

public interface SchoolClassRepository extends CrudRepository<SchoolClass, Long> {
}
