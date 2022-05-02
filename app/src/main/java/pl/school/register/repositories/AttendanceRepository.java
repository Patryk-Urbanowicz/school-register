package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Attendance;

public interface AttendanceRepository extends CrudRepository<Attendance, Long> {
}
