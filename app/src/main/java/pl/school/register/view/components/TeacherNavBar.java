package pl.school.register.view.components;

import com.vaadin.flow.component.html.Label;
import pl.school.register.model.Teacher;

public class TeacherNavBar extends NavBar{
    public TeacherNavBar(Teacher teacher){
        add(new Label(String.format("%s %s", teacher.getFirstName(), teacher.getLastName())));
    }
}
