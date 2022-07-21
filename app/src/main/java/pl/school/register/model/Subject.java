package pl.school.register.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String subjectName;

    @OneToMany(mappedBy = "subject")
    @EqualsAndHashCode.Exclude
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToMany(mappedBy = "subjects")
    @EqualsAndHashCode.Exclude
    private Set<Teacher> teachers = new HashSet<>();
}
