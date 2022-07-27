package pl.school.register.view;

import com.vaadin.flow.component.applayout.AppLayout;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.Parent;
import pl.school.register.service.ParentService;
import pl.school.register.view.components.NavBar;

public class ParentLayout extends AppLayout {
    public ParentLayout(ParentService parentService){
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        Parent parent = parentService.getByLogin(userDetails.getUsername());
        addToNavbar(new NavBar(parent));
    }
}
