package pl.school.register.view.components;

import com.vaadin.flow.component.html.*;

import pl.school.register.view.components.*;

public class ColumnLabel extends ColumnSegment{
	public ColumnLabel(String weekday){
		setClassName("column-label");
		Label weekdayLabel = new Label(weekday);
		add(weekdayLabel);
	}
}
