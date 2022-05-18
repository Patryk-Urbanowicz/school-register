package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(value = "SELECT * FROM lesson WHERE school_class_id = :school_class_id", nativeQuery = true)
    List<Lesson> findLessonBySchoolClassId(@Param("school_class_id") Long school_class_id);

    @Query(value = "SELECT * FROM lesson WHERE teacher_id = :teacher_id", nativeQuery = true)
    List<Lesson> findLessonByTeacherId(@Param("teacher_id") Long teacher_id);

    @Query(value = "SELECT * FROM lesson WHERE subject_id = :subject_id", nativeQuery = true)
    List<Lesson> findLessonBySubjectId(@Param("subject_id") Long subject_id);

}