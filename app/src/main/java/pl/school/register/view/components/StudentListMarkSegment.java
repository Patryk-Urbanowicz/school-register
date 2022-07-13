package pl.school.register.view.components;

import com.vaadin.flow.component.html.Label;
import pl.school.register.model.Mark;

public class StudentListMarkSegment extends RowSegment{
    public StudentListMarkSegment(Mark mark){
        Label markLabel = new Label(mark.getValue().toString());
        add(markLabel);
    }
}
