package pl.school.register.view;

import com.vaadin.flow.component.applayout.AppLayout;
import pl.school.register.view.components.NavBar;
import com.vaadin.flow.component.dependency.*;

@CssImport("./themes/style.css")
public class AccountMainLayout extends AppLayout {
    AccountMainLayout() {
        addToNavbar(new NavBar());
    }
}
