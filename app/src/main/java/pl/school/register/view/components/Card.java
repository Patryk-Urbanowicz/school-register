package pl.school.register.view.components;

import com.vaadin.flow.component.orderedlayout.*;
import com.vaadin.flow.component.*;

public class Card extends VerticalLayout{
	public Card(String HEIGHT){
		setClassName("left-panel-card");
		setHeight(HEIGHT);
	}
}
