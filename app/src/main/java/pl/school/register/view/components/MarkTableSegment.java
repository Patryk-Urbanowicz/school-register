package pl.school.register.view.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import pl.school.register.model.Mark;

import java.util.List;

public class MarkTableSegment extends RowSegment{
    public MarkTableSegment(List<Mark> marks){
        HorizontalLayout marksLayout = new HorizontalLayout();
        for (Mark m: marks){
            marksLayout.add(new MarkComponent(m));
        }
        add(marksLayout);
    }
}
