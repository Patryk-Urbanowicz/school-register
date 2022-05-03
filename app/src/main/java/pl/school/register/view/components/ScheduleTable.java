package pl.school.register.view.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.enumerations.WeekDay;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleTable extends HorizontalLayout {
    public ScheduleTable(List<LessonBlock> lessons) {
        setSpacing(false);
        setClassName("schedule-table");
        List<String> weekdays = Arrays.stream(WeekDay.values())
                                    .map(WeekDay::name)
                                    .collect(Collectors.toList());
		weekdays.remove(WeekDay.SATURDAY.name());
		weekdays.remove(WeekDay.SUNDAY.name());
        weekdays.forEach(weekday -> {
            List<LessonBlock> lessonOnThisDay = lessons.stream()
                            .filter(lessonBlock -> weekday.equals(lessonBlock.getWeekDay().name()))
                            .collect(Collectors.toList());
            add(new Column(weekday, new ArrayDeque<>(lessonOnThisDay), 8));
        });

    }
}
