package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.LessonBlock;

public interface LessonBlockRepository extends CrudRepository<LessonBlock, Long> {
}
