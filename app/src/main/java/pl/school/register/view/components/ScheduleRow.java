package pl.school.register.view.components;

import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Teacher;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.model.projections.MeetingInWeek;

import java.util.Deque;
import java.util.Locale;

public class ScheduleRow extends Row{
    public ScheduleRow(Deque<MeetingInWeek> meetings, String hour){
        int hourInt = Integer.parseInt(hour.split(":")[0]);
        String hourString = String.format("%s-%d:%d", hour, hourInt, 55);
        add(new RowSegment(hourString));
        for (int i=0; i < 5; i++){
            if (!meetings.isEmpty()){
                MeetingInWeek meeting = meetings.peek();
                WeekDay currentDay = WeekDay.values()[i];
                if (currentDay.equals(meeting.getWeekDay())){
                    add(new ScheduleSegment(
                            "#00FF00",
                            meeting.getSubjectName(),
                            meeting.getRoom(),
                            getTeacherShort(meeting.getTeacherFirstName(), meeting.getTeacherLastName())
                    ));
                    meetings.pop();
                    continue;
                }
            }
            add(new RowSegment());
        }
    }

    private String getTeacherShort(String first, String last) {
        return (String.format("%s%s", first.charAt(0), last.charAt(0))).toUpperCase(Locale.ROOT);
    }

    private String getHoursString(String hour){
        String nextHour = String.valueOf(Integer.parseInt(hour.split(":")[0])+1);
        return String.format("%s-%s:00",hour,nextHour);
    }
}
