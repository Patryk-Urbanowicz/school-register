package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Lesson;
import pl.school.register.model.Mark;
import pl.school.register.model.SubjectAvgPair;

import java.util.List;
import java.util.Optional;


@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {
    List<Mark> findAllByStudentId(@Param("student_id") Long student_id);

    List<Mark> findAllByStudentIdAndLessonId(@Param("student_id") Long student_id, @Param("lesson_id") Long subject_id);

    @Query(value = "SELECT \n" +
            "    cast(sum(m.value * m.weight) AS float) / sum(m.weight) as w_avg \n" +
            "FROM mark AS m WHERE student_id=:student_id AND lesson_id=:lesson_id", nativeQuery = true)
    Optional<Float> findWeightedAverageByStudentIdAndLessonId(@Param("student_id") Long student_id,
                                                              @Param("lesson_id") Long lesson_id);

    @Query(value = "SELECT \n" +
            "    s.subject_name, \n" +
            "    cast(sum(m.value * m.weight) AS float) / sum(m.weight) as w_avg\n" +
            "FROM lesson JOIN mark m on lesson.id = m.lesson_id\n" +
            "            JOIN subject s on lesson.subject_id = s.id\n" +
            "                  WHERE school_class_id = :school_class_id AND m.student_id = :student_id " +
            "group by s.subject_name", nativeQuery = true)
    List<SubjectAvgPair> findAllWeightedAverageForStudentGroupingBySubject(
            @Param("school_class_id") Long school_class_id,
            @Param("student_id") Long student_id);
}
