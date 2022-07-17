package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Teacher;

@Data
@NoArgsConstructor
public class TeacherDTO extends AccountDTO{
    public TeacherDTO(Teacher teacher) {
        super(teacher);
    }
}
