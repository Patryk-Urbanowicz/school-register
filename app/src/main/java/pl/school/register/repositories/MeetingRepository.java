package pl.school.register.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.school.register.model.Meeting;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {
}
