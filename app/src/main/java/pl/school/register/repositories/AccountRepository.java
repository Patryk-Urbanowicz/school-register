package pl.school.register.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.school.register.model.Account;
import pl.school.register.model.projections.AccountInfo;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT role, first_name, last_name, login FROM account WHERE id = :id", nativeQuery = true)
    AccountInfo findAccountInfoById(@Param("id") Long id);

    AccountInfo findAccountInfoByLogin(String login);

    Account findByLogin(String username);
}
