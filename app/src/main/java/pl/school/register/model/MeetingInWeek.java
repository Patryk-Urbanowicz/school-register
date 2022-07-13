package pl.school.register.model;

import pl.school.register.model.enumerations.WeekDay;

//horrible name
public interface MeetingInWeek {
    Meeting getMeeting();
    WeekDay getWeekDay();
}
