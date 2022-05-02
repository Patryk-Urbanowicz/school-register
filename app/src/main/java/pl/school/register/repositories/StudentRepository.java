package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
