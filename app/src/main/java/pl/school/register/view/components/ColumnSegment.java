package pl.school.register.view.components;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.*;

public class ColumnSegment extends VerticalLayout{
	public ColumnSegment(){
		setWidth("150px");
		setHeight("50px");
		getStyle()
				.set("padding", "0")
				.set("border-style", "none none  solid none")
				.set("border-width", "1px")
				.set("border-color", "black");
	}
}
