package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Attendance;
import pl.school.register.model.LessonBlock;

import java.util.List;

@Repository
public interface LessonBlockRepository extends JpaRepository<LessonBlock, Long> {

    @Query(value = "SELECT * FROM lesson_block WHERE lesson_id = :lesson_id", nativeQuery = true)
    List<LessonBlock> findLessonBlocksByLessonId(@Param("lesson_id") Long lesson_id);
}
