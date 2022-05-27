package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import java.util.Collection;

/*
    Not really a view as it isn't displayed (or rather it shouldn't).
    I just need to redirect the user after login based on their role somehow
    and this seems to be the most sane way to do so.
 */

@Route(value = "")
@RolesAllowed(value = {"STUDENT", "TEACHER", "PARENT"})
public class AfterLoginRedirectView extends VerticalLayout implements BeforeEnterObserver {
    public AfterLoginRedirectView(){
        //Nothing happening here
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        /* UserDetails should be obtained from a security service */
        //TODO: Change later when backed is ready
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorityCollection = userDetails.getAuthorities();
        if (authorityCollection.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))){
            beforeEnterEvent.forwardTo(StudentScheduleView.class);
        }else if (authorityCollection.contains(new SimpleGrantedAuthority("TEACHER"))){
            beforeEnterEvent.rerouteTo("/teacher");
        }
    }
}
