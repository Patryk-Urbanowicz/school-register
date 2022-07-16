package pl.school.register.model.dto;

import lombok.Data;
import pl.school.register.model.Mark;
import java.sql.Timestamp;

@Data
public class MarkDTO {
    private Long id;
    private Long studentId;
    private Long teacherId;
    private Integer value;
    private Integer weight;
    private String label;
    private String description;
    private Timestamp timestamp;
    private Long lessonId;

    public MarkDTO(Mark mark){
        this.id = mark.getId();
        this.studentId = mark.getStudent().getId();
        this.teacherId = mark.getTeacher().getId();
        this.value = mark.getValue();
        this.weight = mark.getWeight();
        this.label = mark.getLabel();
        this.description = mark.getDescription();
        this.timestamp = mark.getTimestamp();
        this.lessonId = mark.getLesson().getId();
    }
}
