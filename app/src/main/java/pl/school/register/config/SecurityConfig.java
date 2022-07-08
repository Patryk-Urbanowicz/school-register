package pl.school.register.config;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.school.register.service.AccountDetailsService;
import pl.school.register.service.AccountService;

@EnableWebSecurity
public class SecurityConfig {
    @Order(1)
    @Configuration
    class RESTSecurityConfig extends WebSecurityConfigurerAdapter{

        private AccountDetailsService accountDetailsService;
        private PasswordEncoder passwordEncoder;

        public RESTSecurityConfig(AccountDetailsService accountDetailsService, PasswordEncoder passwordEncoder) {
            this.accountDetailsService = accountDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .formLogin().disable()
                    .antMatcher("/api/**");
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/images/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Order(2)
    @Configuration
    class VaadinSecurityConfig extends VaadinWebSecurityConfigurerAdapter{

        private AccountDetailsService accountDetailsService;
        private PasswordEncoder passwordEncoder;

        public VaadinSecurityConfig(AccountDetailsService accountDetailsService, PasswordEncoder passwordEncoder) {
            this.accountDetailsService = accountDetailsService;
            this.passwordEncoder = passwordEncoder;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .antMatcher("/**")
                    .authorizeRequests()
                    .anyRequest().permitAll();
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/images/**");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(accountDetailsService).passwordEncoder(passwordEncoder);
        }
    }
}
