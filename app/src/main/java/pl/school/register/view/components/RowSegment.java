package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;

@Tag(value = "td")
public class RowSegment extends Div {
	public RowSegment(){
		addClassName("row-segment");
	}

	public RowSegment(String labelText){
		addClassName("row-segment");
		addClassName("hour-segment");
		Label label = new Label(labelText);
		add(label);
	}
}
