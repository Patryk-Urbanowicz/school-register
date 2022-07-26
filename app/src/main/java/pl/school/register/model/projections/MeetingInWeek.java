package pl.school.register.model.projections;

import pl.school.register.model.Meeting;
import pl.school.register.model.enumerations.AttendanceStatus;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.service.AttendanceService;

import java.time.LocalDateTime;

//horrible name
public interface MeetingInWeek {
    Long getId();
    String getTeacherFirstName();
    String getTeacherLastName();
    LocalDateTime getLessonStartTime();
    String getSubjectName();
    WeekDay getWeekDay();
    String getStartTime();
    String getRoom();
}