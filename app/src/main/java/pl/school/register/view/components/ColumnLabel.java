package pl.school.register.view.components;

import com.vaadin.flow.component.html.*;

import pl.school.register.view.components.*;

public class ColumnLabel extends ColumnSegment{
	public ColumnLabel(String weekday){
		setHeight("25px");
		getStyle()
				.set("align-items", "center")
				.set("background", "#696969");

		Label weekdayLabel = new Label(weekday);
		add(weekdayLabel);
	}
}
