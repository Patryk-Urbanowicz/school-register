package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.school.register.model.Meeting;
import pl.school.register.model.Student;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.AttendanceService;
import pl.school.register.service.MeetingService;
import pl.school.register.service.StudentService;
import pl.school.register.view.components.dialog.MeetingDialog;

import java.util.*;
import java.util.stream.Collectors;

@Tag(value = "table")
public class ScheduleTable extends Div {
    private MeetingService meetingService;
    private StudentService studentService;
    private AttendanceService attendanceService;
    private boolean isTeacher;
    public ScheduleTable(List<MeetingInWeek> meetings, MeetingService meetingService,
                         StudentService studentService, AttendanceService attendanceService){
        new ScheduleTable(meetings, meetingService, studentService, attendanceService, false);
    }
    public ScheduleTable(List<MeetingInWeek> meetings, MeetingService meetingService,
                         StudentService studentService, AttendanceService attendanceService, boolean isTeacher) {
        this.meetingService = meetingService;
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.isTeacher = isTeacher;
        addClassName("schedule-table");
        List<String> lessonHours = List.of("8:00","9:00","10:00","11:00","12:00","13:00","14:00","15:00");
        List<WeekDay> days = Arrays.stream(WeekDay.values()).collect(Collectors.toList());
        days.remove(WeekDay.SATURDAY);
        days.remove(WeekDay.SUNDAY);
        Row thead = new Row(days.toArray(new WeekDay[1]));
        thead.addClassName("table-head");
        add(thead);
        lessonHours.forEach(hour -> {
                    List<MeetingInWeek> meetings2 = meetings.stream()
                            .filter(meeting -> hour.equals(meeting.getStartTime()))
                            .collect(Collectors.toList());
                    add(new ScheduleRow(new ArrayDeque<>(meetings2), hour));
                });
    }

    private class ScheduleRow extends Row{
        public ScheduleRow(Deque<MeetingInWeek> meetings, String hour){
            int hourInt = Integer.parseInt(hour.split(":")[0]);
            String hourString = String.format("%s-%d:%d", hour, hourInt, 55);
            add(new RowSegment(hourString));
            for (int i=0; i < 5; i++){
                if (!meetings.isEmpty()){
                    MeetingInWeek meeting = meetings.peek();
                    WeekDay currentDay = WeekDay.values()[i];
                    if (currentDay.equals(meeting.getWeekDay())){
                        add(new ScheduleSegment(meeting));
                        meetings.pop();
                        continue;
                    }
                }
                add(new RowSegment());
            }
        }
    }

    private class ScheduleSegment extends RowSegment {
        public ScheduleSegment(MeetingInWeek meeting){
            addClassName("schedule-segment");
            Label lessonNameLabel = new Label(meeting.getSubjectName());
            HorizontalLayout bottom = new HorizontalLayout();
            Label classroom = new Label(meeting.getRoom());
            Label teacherShortLabel = new Label(getTeacherShort(meeting.getTeacherFirstName(), meeting.getTeacherFirstName()));
            bottom.setClassName("bottom-element");
            bottom.add(classroom, teacherShortLabel);
            if (isTeacher){
                Optional<Meeting> m1 = meetingService.getById(meeting.getId());
                m1.ifPresent(value -> this.addClickListener(listener -> {
                    MeetingDialog md = new MeetingDialog();
                    md.setMeeting(value);
                    md.setStudentService(studentService);
                    md.setAttendanceService(attendanceService);
                    md.setMeetingService(meetingService);
                    md.build();
                    md.open();
                }));
            }
            add(lessonNameLabel, bottom);
        }

        private String getTeacherShort(String first, String last) {
            return (String.format("%s%s", first.charAt(0), last.charAt(0))).toUpperCase(Locale.ROOT);
        }

    }
}
