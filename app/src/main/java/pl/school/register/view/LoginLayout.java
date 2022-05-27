package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.Collection;

@Route(value = "login")
@PermitAll
public class LoginLayout extends VerticalLayout implements BeforeEnterObserver {
    private final LoginForm form = new LoginForm();
    public LoginLayout(){
        form.setAction("login");
        add(
                new H1("Awooga"),
                form
        );
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
            form.setError(true);
        }
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
            if (authorityCollection.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
                beforeEnterEvent.forwardTo(StudentScheduleView.class);
            }else if (authorityCollection.contains(new SimpleGrantedAuthority("TEACHER"))){
                beforeEnterEvent.rerouteTo("/teacher");
            }
        }

    }
}
