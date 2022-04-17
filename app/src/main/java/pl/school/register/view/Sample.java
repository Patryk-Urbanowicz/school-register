package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "")
public class Sample extends VerticalLayout {
    public Sample(){
        setSizeFull();
        add(new H1("Hello from Vaadin! (this will be deleted later)"));
    }
}
