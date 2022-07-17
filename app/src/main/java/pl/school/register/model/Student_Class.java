package pl.school.register.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Student_Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @NotNull
    private SchoolClass schoolClass;

    @OneToOne
    @NotNull
    private Student student;

    @NotNull
    private Long index;
}
