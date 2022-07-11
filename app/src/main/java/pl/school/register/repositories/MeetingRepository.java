package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Meeting;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {


}
