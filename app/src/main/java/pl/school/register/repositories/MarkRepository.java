package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Lesson;
import pl.school.register.model.Mark;

import java.util.List;
import java.util.Optional;


@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

   @Query(value = "SELECT * FROM mark WHERE student_id = :student_id", nativeQuery = true)
    List<Mark> findMarksForStudentId(@Param("student_id") Long student_id);

    @Query(value = "SELECT * FROM mark WHERE student_id = :student_id AND subject_id = :subject_id", nativeQuery = true)
    List<Mark> findMarksForStudentIdAndSubjectId(@Param("student_id") Long student_id, @Param("subject_id") Long subject_id);
}
