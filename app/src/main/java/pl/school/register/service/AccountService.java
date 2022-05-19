package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Account;
import pl.school.register.model.AccountInfo;
import pl.school.register.repositories.AccountRepository;

import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void addAccount(Account account){
        accountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return accountRepository.findById(id);
    }

    public AccountInfo getAccountInfo(Long id) {
        return accountRepository.findAccountInfoById(id);
    }
}
