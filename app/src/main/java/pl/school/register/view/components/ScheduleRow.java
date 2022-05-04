package pl.school.register.view.components;

import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Teacher;
import pl.school.register.model.enumerations.WeekDay;

import java.util.Deque;
import java.util.Locale;

public class ScheduleRow extends Row{
    public ScheduleRow(Deque<LessonBlock> lessonBlocks, String hour){
        int hourInt = Integer.parseInt(hour.split(":")[0]);
        String hourString = String.format("%s-%d:%d", hour, hourInt, 55);
        add(new RowSegment(hourString));
        for (int i=0; i < 5; i++){
            if (!lessonBlocks.isEmpty()){
                LessonBlock block = lessonBlocks.peek();
                WeekDay currentDay = WeekDay.values()[i];
                if (currentDay.equals(block.getWeekDay())){
                    Lesson lesson = block.getLesson();
                    add(new ScheduleSegment(
                            "#00FF00",
                            lesson.getSubject().getSubjectName(),
                            "1",
                            getTeacherShort(lesson.getTeacher())
                    ));
                    lessonBlocks.pop();
                    continue;
                }
            }
            add(new RowSegment());
        }
    }

    private String getTeacherShort(Teacher teacher) {
        return (String.format("%s%s", teacher.getFirstName().charAt(0), teacher.getLastName().charAt(0))).toUpperCase(Locale.ROOT);
    }

    private String getHoursString(String hour){
        String nextHour = String.valueOf(Integer.parseInt(hour.split(":")[0])+1);
        return String.format("%s-%s:00",hour,nextHour);
    }
}
