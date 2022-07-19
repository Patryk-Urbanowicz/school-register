package pl.school.register.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    Lesson lesson;

    @OneToMany(mappedBy = "meeting")
    @EqualsAndHashCode.Exclude
    Set<Attendance> attendances = new HashSet<>();

    @ManyToOne
    @NotNull
    Teacher teacher;

    @NotNull
    LocalDateTime time;
    String topic;

    @NotNull
    String room;
}
