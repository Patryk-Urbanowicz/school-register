package pl.school.register.model.projections;

import pl.school.register.model.enumerations.Role;

public interface AccountInfo {
    Long getId();
    String getFirstName();
    String getLastName();
    String getLogin();
    Role getRole();
}
