package pl.school.register.view;

import com.vaadin.flow.component.applayout.AppLayout;
import pl.school.register.view.components.NavBar;

public class AccountMainLayout extends AppLayout {
    AccountMainLayout() {
        addToNavbar(new NavBar());
    }
}
