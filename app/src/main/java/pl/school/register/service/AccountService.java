package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Account;
import pl.school.register.model.AccountInfo;
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

    public void addAccount(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public AccountInfo getAccountInfo(Long id) {
        return accountRepository.findAccountInfoById(id);
    }

    public AccountInfo getInfoByLogin(String login){
        return accountRepository.findAccountInfoByLogin(login);
    }
}
