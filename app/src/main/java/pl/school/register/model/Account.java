package pl.school.register.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.school.register.model.enumerations.Role;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Data
abstract public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be blank")
    private String firstName;

    @NotNull(message = "first name cannot be null")
    @NotBlank(message = "first name cannot be blank")
    private String lastName;
    @NotNull(message = "login cannot be null")
    @NotBlank(message = "login cannot be blank")
    private String login;
    @NotNull(message = "password cannot be null")
    @Length(min = 8, max = 256, message = "Password must be between 8 and 256 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false, nullable = false)
    private Role role;
}
