package pl.school.register.view.components;

import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.*;

public class Card extends VerticalLayout{
	public Card(String HEIGHT){
		getStyle()
				.set("padding","10px")
				.set("background", "white")
				.set("border", "1px solid grey");
		setHeight(HEIGHT);
	
	}
}
