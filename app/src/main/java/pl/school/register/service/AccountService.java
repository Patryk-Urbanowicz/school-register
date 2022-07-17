package pl.school.register.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.school.register.model.Account;
import pl.school.register.model.projections.AccountInfo;
import pl.school.register.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder){
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account addNew(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<Account> getById(Long id) {
        return accountRepository.findById(id);
    }

    public AccountInfo getInfoById(Long id) {
        return accountRepository.findInfoById(id);
    }

    public AccountInfo getInfoByLogin(String login){
        return accountRepository.findAccountInfoByLogin(login);
    }
}
