package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Mark;
import pl.school.register.model.Parent;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {

    @Query(value = "SELECT * FROM parent_student WHERE student_id = :student_id", nativeQuery = true)
    Optional<Parent> findByStudentId(@Param("student_id") Long student_id);


}
