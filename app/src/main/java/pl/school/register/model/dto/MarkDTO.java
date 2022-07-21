package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import pl.school.register.model.Lesson;
import pl.school.register.model.Mark;
import pl.school.register.model.Student;
import pl.school.register.model.Teacher;
import pl.school.register.model.dto.mapper.MappingUtils;

import java.sql.Timestamp;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class MarkDTO implements DTO {
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

    public MarkDTO(Long id, Long studentId, Long teacherId, Integer value, Integer weight, String label,
                   String description, Timestamp timestamp, Long lessonId) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.value = value;
        this.weight = weight;
        this.label = label;
        this.description = description;
        this.timestamp = timestamp;
        this.lessonId = lessonId;
    }

    @Override
    public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
        mapper.addMappings(markMap(utils));
        return mapper;
    }

    public PropertyMap<MarkDTO, Mark> markMap(MappingUtils utils) {
        return new PropertyMap<MarkDTO, Mark>() {
            @Override
            protected void configure() {
                Converter<MarkDTO, Mark> converter = new AbstractConverter<MarkDTO, Mark>() {
                    @Override
                    protected Mark convert(MarkDTO markDTO) {
                        Mark mark = new Mark();
                        Teacher teacher = new Teacher();
                        Student student = new Student();
                        Lesson lesson = new Lesson();
                        teacher.setId(markDTO.getTeacherId());
                        student.setId(markDTO.getStudentId());
                        lesson.setId(markDTO.getLessonId());

                        mark.setTeacher(teacher);
                        mark.setStudent(student);
                        mark.setLesson(lesson);
                        mark.setValue(markDTO.getValue());
                        mark.setWeight(markDTO.getWeight());
                        mark.setLabel(markDTO.getLabel());
                        mark.setDescription(markDTO.getDescription());

                        return mark;
                    }
                };
            }
        };
    };
}
