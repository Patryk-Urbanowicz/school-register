package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Parent;
import pl.school.register.model.SchoolClass;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {
    Optional<SchoolClass> findByHomeroomTeacherId(@Param("homeroom_teacher_id") Long homeroom_teacher_id);

    @Query(value = "SELECT DISTINCT sc.* FROM school_class sc " +
                    "JOIN lesson l ON sc.id = l.school_class_id " +
                    "WHERE l.teacher_id = :teacher_id", nativeQuery = true)
    List<SchoolClass> findAllByForTeacherWhoHasLessonsWith(@Param("teacher_id") Long teacher_id);
}
