package pl.school.register.view.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import pl.school.register.model.Mark;

public class MarkComponent extends Div {
    private Mark mark;
    public MarkComponent(Mark mark){
        this.mark = mark;
        add(new MarkValueComponent(mark), new MarkInfoComponent(mark));
    }
}
