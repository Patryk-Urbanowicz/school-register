package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.school.register.model.Meeting;
import pl.school.register.model.MeetingInWeek;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    @Query(value = "SELECT m.id, m.room, m.time, m.teacher_id, m.lesson_id, lb.week_day FROM meeting m " +
                    "JOIN lesson ON m.lesson_id = lesson.id " +
                    "JOIN lesson_block lb ON lb.lesson_id = lesson.id " +
                    "WHERE m.teacher_id = :teacher_id AND lesson.school_class_id = :school_class_id AND lesson.subject_id = :subject_id " +
                    "AND m.time BETWEEN :start AND :end", nativeQuery = true)
    List<MeetingInWeek> findAllTeacherMeetingsInWeek(@Param("teacher_id") Long teacher_id,
                                                 @Param("school_class_id") Long school_class_id,
                                                 @Param("subject_id") Long subject_id,
                                                 @Param("start") LocalDate start,
                                                 @Param("end") LocalDate end);

}
