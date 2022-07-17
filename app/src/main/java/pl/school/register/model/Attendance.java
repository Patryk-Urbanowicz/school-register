package pl.school.register.model;

import lombok.Data;
import pl.school.register.model.enumerations.AttendanceStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Meeting meeting;

    @Enumerated
    @Column(name = "status")
    @NotNull
    private AttendanceStatus attendanceStatus;
}