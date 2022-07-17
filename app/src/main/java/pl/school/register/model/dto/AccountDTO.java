package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Account;
import pl.school.register.model.enumerations.Role;

@Data
@NoArgsConstructor
public abstract class AccountDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Role role;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.login = account.getLogin();
        this.password = account.getPassword();
        this.role = account.getRole();
    }
}
