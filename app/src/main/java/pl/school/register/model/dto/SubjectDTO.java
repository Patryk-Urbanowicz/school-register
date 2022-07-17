package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Subject;

@Data
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String subjectName;

    public SubjectDTO(Subject subject) {
        this.id = subject.getId();
        this.subjectName = subject.getSubjectName();
    }
}
