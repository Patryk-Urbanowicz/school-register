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
    List<Attendance> findAllByStudentId(@Param("student_id") Long student_id);

    List<Attendance> findAllByMeetingIdOrderById(@Param("meeting_id") Long meeting_id);

    @Query(value = "SELECT attendance.* FROM attendance JOIN meeting m on attendance.meeting_id = m.id\n" +
            "    WHERE m.lesson_id=:lesson_id AND attendance.student_id=:student_id", nativeQuery = true)
    List<Attendance> findAttendanceByLessonIdAndStudentId(@Param("lesson_id") Long lesson_id,
                                                          @Param("student_id") Long student_id);

    Attendance findByMeetingIdAndStudentId(Long meeting_id, Long student_id);
}
