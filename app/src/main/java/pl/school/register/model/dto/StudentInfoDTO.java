package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Account;
import pl.school.register.model.Student;
import pl.school.register.model.enumerations.Role;

@Data
@NoArgsConstructor
public class StudentInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private Role role;
    private Long schoolClassId;
    private Long index;

    public StudentInfoDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.login = student.getLogin();
        this.role = student.getRole();
        this.schoolClassId = student.getSchoolClass().getId();
        this.index = student.getIndex();
    }
}
