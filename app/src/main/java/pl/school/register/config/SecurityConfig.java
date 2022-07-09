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
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.school.register.filter.AuthenticationFilter;
import pl.school.register.filter.AuthorizationFilter;
import pl.school.register.service.AccountDetailsService;

@EnableWebSecurity
public class SecurityConfig {
    @Order(1)
    @Configuration
    class RESTSecurityConfig extends WebSecurityConfigurerAdapter{

        private final AccountDetailsService accountDetailsService;
        private final PasswordEncoder passwordEncoder;
        private final JWTConfig jwtConfig;

        public RESTSecurityConfig(AccountDetailsService accountDetailsService, PasswordEncoder passwordEncoder, JWTConfig jwtConfig) {
            this.accountDetailsService = accountDetailsService;
            this.passwordEncoder = passwordEncoder;
            this.jwtConfig = jwtConfig;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            AuthenticationFilter filter = new AuthenticationFilter(authenticationManagerBean(), jwtConfig);
            filter.setFilterProcessesUrl("/api/account/login");
            http
                    .csrf().disable()
                    .formLogin().disable()
                    .antMatcher("/api/**")
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers("/api/student/**").hasAuthority("STUDENT")
                    .and()
                    .addFilter(filter)
                    .addFilterBefore(new AuthorizationFilter(accountDetailsService, jwtConfig), UsernamePasswordAuthenticationFilter.class);

        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/images/**",
                    "/api/example");
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
