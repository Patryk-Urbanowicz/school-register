package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Label;
import pl.school.register.model.Student;

@Tag("td")
public class StudentListSegment extends RowSegment{
    public StudentListSegment(String  studentNameString, Long index){
        Label name = new Label(studentNameString);
        Label idx = new Label(index.toString());
        add(name, idx);
    }
}
