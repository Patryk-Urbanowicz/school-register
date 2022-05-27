package pl.school.register.config;

import com.vaadin.flow.spring.security.VaadinWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.school.register.view.LoginLayout;

@EnableWebSecurity
public class SecurityConfig {
    @Order(1)
    @Configuration
    class RESTSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .formLogin().disable()
                    .antMatcher("/api/**");
        }
    }

    @Order(2)
    @Configuration
    class VaadinSecurityConfig extends VaadinWebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            super.configure(http);
            http.antMatcher("/**");
            setLoginView(http, LoginLayout.class);
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/images/**");
        }

        @Override
        protected UserDetailsService userDetailsService() {
            UserDetails student = User
                    .withUsername("joe")
                    .password("{noop}12345678")
                    .roles("STUDENT")
                    .build();
            UserDetails parent = User
                    .withUsername("maria")
                    .password("{noop}joethebest")
                    .roles("PARENT")
                    .build();
            UserDetails teacher = User
                    .withUsername("andrew")
                    .password("{noop}renatixon")
                    .roles("TEACHER")
                    .build();
            return new InMemoryUserDetailsManager(student, parent, teacher);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService());
        }
    }
}
