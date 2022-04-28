package pl.school.register.view.components;

import java.util.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.orderedlayout.*;

import pl.school.register.view.components.*;

public class Column extends VerticalLayout{
	//TODO:
	//There should be an array of lessons
	//We should iterate over it and add ColumnSegments in a loop
	public Column(String weekday, int colnum){
		setWidth("152px");
		getStyle()
				.set("padding", "0")
				.set("border-style", "none solid none solid")
				.set("border-width", "1px")
				.set("border-color", "black");
		setSpacing(false);
		ColumnLabel cl = new ColumnLabel(weekday);
		ArrayList<ColumnSegment> columnSegments = new ArrayList<>();
		for(int i=0; i<colnum; i++){
			columnSegments.add(new ColumnSegment());
		}
		add(cl);
		add(columnSegments.toArray(new ColumnSegment[1]));
	}
}
