package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
