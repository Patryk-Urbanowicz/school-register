package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByLogin(String login);
}
