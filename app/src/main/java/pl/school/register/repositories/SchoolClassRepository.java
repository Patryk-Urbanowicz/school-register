package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Parent;
import pl.school.register.model.SchoolClass;

import java.util.Optional;

@Repository
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {

    @Query(value = "SELECT * FROM school_class WHERE homeroom_teacher_id = :homeroom_teacher_id", nativeQuery = true)
    Optional<SchoolClass> findSchoolClassByHomeroomTeacherId(@Param("homeroom_teacher_id") Long homeroom_teacher_id);
}
