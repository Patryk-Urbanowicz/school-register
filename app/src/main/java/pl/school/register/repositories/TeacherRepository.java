package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
}
