package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Lesson;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
}
