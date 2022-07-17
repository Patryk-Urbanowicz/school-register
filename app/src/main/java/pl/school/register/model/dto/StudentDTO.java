package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Student;

@Data
@NoArgsConstructor
public class StudentDTO extends AccountDTO{
    private Long schoolClassId;
    private Long studentClassId;

    public StudentDTO(Student student) {
        super(student);
        this.schoolClassId = student.getSchoolClass().getId();
        this.studentClassId = student.getStudentClass().getId();
    }
}
