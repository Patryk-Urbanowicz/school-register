package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "account/marks", layout = AccountMiddlePanelNestedLayout.class)
public class StudentMarksView extends VerticalLayout {
    public StudentMarksView(){
        H1 h1 = new H1("WIP: Student's marks will show up here");
        h1.getStyle().set("color", "#303030");
        add(h1);
    }
}
