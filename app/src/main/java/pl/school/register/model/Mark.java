package pl.school.register.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Data
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private Student student;

    @ManyToOne
    @NotNull
    private Teacher teacher;

    @NotNull
    @Min(1)
    @Max(6)
    private Integer value;

    @NotNull
    @Min(0)
    private Integer weight;

    private String label;
    private String description;

    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @NotNull
    private Lesson lesson;
}
