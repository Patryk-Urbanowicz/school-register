package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.model.projections.MeetingInWeek;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Tag(value = "table")
public class ScheduleTable extends Div {
    public ScheduleTable(List<MeetingInWeek> meetings) {
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
}
