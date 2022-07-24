package pl.school.register.model.projections;

import pl.school.register.model.Meeting;
import pl.school.register.model.enumerations.WeekDay;

import java.time.LocalDateTime;

//horrible name
public interface MeetingInWeek {
    String getTeacherFirstName();
    String getTeacherLastName();
    LocalDateTime getLessonStartTime();
    String getSubjectName();
    WeekDay getWeekDay();
    String getStartTime();
    String getRoom();
}