package pl.school.register.model.dto;

import lombok.Data;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.enumerations.WeekDay;

@Data
public class LessonBlockDTO {
    private Long id;
    private WeekDay weekDay;
    private String startTime;
    private Integer duration;
    private LessonDTO lesson;

    public LessonBlockDTO(LessonBlock lessonBlock){
        this.id = lessonBlock.getId();
        this.weekDay = lessonBlock.getWeekDay();
        this.startTime = lessonBlock.getStartTime();
        this.duration = lessonBlock.getDuration();
        this.lesson = new LessonDTO(lessonBlock.getLesson());
    }
}
