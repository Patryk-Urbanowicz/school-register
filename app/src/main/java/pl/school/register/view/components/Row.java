package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import pl.school.register.model.enumerations.WeekDay;

import java.util.Arrays;

@Tag(value = "tr")
public class Row extends Div {
    public Row(){}

    public Row(WeekDay[] weekDays){
        add(new ColumnLabel("Hours:"));
        Arrays.stream(weekDays).forEach(
                weekDay -> add(new ColumnLabel(weekDay.name()))
        );
    }
}
