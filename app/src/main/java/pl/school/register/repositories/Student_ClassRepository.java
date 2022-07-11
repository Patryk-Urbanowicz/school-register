package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Student_Class;

@Repository
public interface Student_ClassRepository extends JpaRepository<Student_Class, Long> {
}
