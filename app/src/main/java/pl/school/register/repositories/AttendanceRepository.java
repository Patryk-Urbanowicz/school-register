package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Attendance;
import pl.school.register.model.Student;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Query(value = "SELECT * FROM attendance WHERE student_id = :student_id", nativeQuery = true)
    List<Attendance> findAttendancesByStudentId(@Param("student_id") Long student_id);

    @Query(value = "SELECT * FROM attendance WHERE lesson_id = :lesson_id", nativeQuery = true)
    List<Attendance> findAttendancesByLessonId(@Param("lesson_id") Long lesson_id);


}
