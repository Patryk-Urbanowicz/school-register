package pl.school.register.model.dto;

import lombok.Data;
import pl.school.register.model.*;
@Data
public class LessonDTO {
    private Long id;
    private Long teacherId;
    private Long schoolClassId;
    private Long subjectId;

    public LessonDTO(Lesson lesson){
        this.id = lesson.getId();
        this.teacherId = lesson.getTeacher().getId();
        this.schoolClassId = lesson.getSchoolClass().getId();
        this.subjectId = lesson.getSubject().getId();
    }
}
