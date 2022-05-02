package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Mark;

public interface MarkRepository extends CrudRepository<Mark, Long> {
}
