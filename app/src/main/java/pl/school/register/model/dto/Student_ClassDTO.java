package pl.school.register.model.dto;

import pl.school.register.model.Student_Class;

public class Student_ClassDTO {
    private Long id;
    private Long schoolClassId;
    private Long studentId;
    private Long index;

    Student_ClassDTO(Student_Class studentClass) {
        this.id = studentClass.getId();
        this.schoolClassId = studentClass.getSchoolClass().getId();
        this.studentId = studentClass.getStudent().getId();
        this.index = studentClass.getIndex();
    }

}
