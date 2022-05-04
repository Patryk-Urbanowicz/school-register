package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

@Tag(value = "td")
public class ColumnSegment extends Div {
	public ColumnSegment(){
		addClassName("column-segment");
	}

	public ColumnSegment(String labelText){
		addClassName("hour-segment");
		Label label = new Label(labelText);
		add(label);
	}
}
