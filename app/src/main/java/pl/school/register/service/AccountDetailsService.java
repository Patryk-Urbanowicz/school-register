package pl.school.register.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.school.register.model.Account;
import pl.school.register.repositories.AccountRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class AccountDetailsService implements UserDetailsService {
    private AccountRepository accountRepository;
    AccountDetailsService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByLogin(username);
        if (account == null) throw new UsernameNotFoundException("Account not found");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getRole().name()));
        return User.withUsername(account.getLogin())
                .password(account.getPassword())
                .authorities(authorities)
                .build();
    }
}
