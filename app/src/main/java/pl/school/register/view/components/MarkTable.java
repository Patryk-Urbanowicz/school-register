package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import pl.school.register.model.Mark;
import pl.school.register.model.Subject;

import java.util.List;
import java.util.Map;

@Tag(value = "table")
public class MarkTable extends Div {
    public MarkTable(Map<Subject, List<Mark>> map){
        add(new MarkTableLabelRow());
        map.forEach((subject, marks) -> {
            add(new MarkRow(subject, marks));
        });
    }
}
