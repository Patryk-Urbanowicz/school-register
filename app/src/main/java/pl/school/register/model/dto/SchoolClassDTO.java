package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.SchoolClass;

@Data
@NoArgsConstructor
public class SchoolClassDTO {
    private Long id;
    private String className;
    private Long homeroomTeacherId;

    public SchoolClassDTO(SchoolClass schoolClass) {
        this.id = schoolClass.getId();
        this.className = schoolClass.getClassName();
        this.homeroomTeacherId = schoolClass.getHomeroomTeacher().getId();
    }
}