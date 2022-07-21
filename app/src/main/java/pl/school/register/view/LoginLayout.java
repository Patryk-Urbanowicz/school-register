package pl.school.register.view;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.PermitAll;
import java.util.Collection;

@Route(value = "login")
@PermitAll
@CssImport(value = "./themes/login.css")
public class LoginLayout extends VerticalLayout {
    private final LoginForm form = new LoginForm();
    public LoginLayout(){
        setClassName("login-page");
        form.setForgotPasswordButtonVisible(false);
        form.setAction("login");
        Div box = new Div();
        box.setClassName("login-box");
        box.add(form);
        add(
                new H1("Log in to the School Register"),
                box
        );
    }

//    @Override
//    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
//        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")){
//            form.setError(true);
//        }
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (principal instanceof UserDetails){
//            UserDetails userDetails = (UserDetails) principal;
//            Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
//            if (authorityCollection.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
//                beforeEnterEvent.forwardTo(StudentScheduleView.class);
//            }else if (authorityCollection.contains(new SimpleGrantedAuthority("TEACHER"))){
//                beforeEnterEvent.rerouteTo(TeacherLayout.class);
//            }
//        }
//
//    }
}
