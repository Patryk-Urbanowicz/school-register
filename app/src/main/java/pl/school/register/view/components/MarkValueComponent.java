package pl.school.register.view.components;

import com.vaadin.flow.component.html.Label;
import pl.school.register.model.Mark;

public class MarkValueComponent extends Label {
    public MarkValueComponent(Mark mark){
        super(mark.getValue().toString());
        setClassName("mark");
    }
}
