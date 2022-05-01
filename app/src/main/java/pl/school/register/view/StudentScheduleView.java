package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import pl.school.register.view.components.*;

@Route(value = "account/schedule", layout = AccountMiddlePanelNestedLayout.class)
public class StudentScheduleView extends VerticalLayout {
    public StudentScheduleView(){
		//Column c = new Column("monday", 8);
        ScheduleTable st = new ScheduleTable();
		add(st);
    }
}
