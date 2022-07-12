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

    List<Attendance> findAllByMeetingId(@Param("meeting_id") Long meeting_id);
}
