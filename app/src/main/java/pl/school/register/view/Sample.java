package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@Route(value = "")
@PermitAll
public class Sample extends VerticalLayout {
    public Sample(){
        setSizeFull();
        System.out.println("????");
        add(new H1("Hello from Vaadin! (this will be deleted later)"));
    }
}
