package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.server.VaadinService;
import pl.school.register.model.Account;
import pl.school.register.model.Student;
import pl.school.register.service.ParentService;

import java.util.ArrayList;
import java.util.List;


@Tag("navbar")
public class NavBar extends Div {
    public NavBar(Account account){
        Button logout = new Button("Logout");
        logout.addClickListener(buttonClickEvent -> {
           getUI().get().getSession().close();
           VaadinService.getCurrentRequest().getWrappedSession().invalidate();
           getUI().get().getPage().setLocation("/");
        });
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout horizontalLayout = new HorizontalLayout(logout);
        add(horizontalLayout);
        add(new Label(String.format("%s %s", account.getFirstName(), account.getLastName())));
    }

    public NavBar(){
        Button logout = new Button("Logout");
        logout.addClickListener(buttonClickEvent -> {
            getUI().get().getSession().close();
            VaadinService.getCurrentRequest().getWrappedSession().invalidate();
            getUI().get().getPage().setLocation("/");
        });
        logout.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        HorizontalLayout horizontalLayout = new HorizontalLayout(logout);
        add(horizontalLayout);
    }
}
