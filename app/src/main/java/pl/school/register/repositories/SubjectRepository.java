package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
}
