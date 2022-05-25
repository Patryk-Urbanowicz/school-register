package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.*;

@Tag(value = "th")
public class ColumnLabel extends RowSegment {
	public ColumnLabel(String label){
		if (label.toLowerCase().contains("hours")){
			setClassName("hour-segment");
			addClassName("column-label");
			/* When scrolling down make the "Hours:" display over the hour-segment*/
			getStyle().set("z-index", "11");
		}else{
			setClassName("column-label");
		}

		Label weekdayLabel = new Label(label);
		add(weekdayLabel);
	}
}
