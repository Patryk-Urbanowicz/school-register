package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.*;

@Tag(value = "th")
public class ColumnLabel extends RowSegment {
	public ColumnLabel(String label){
		setClassName("column-label");
		Label weekdayLabel = new Label(label);
		add(weekdayLabel);
	}
}
