package pl.school.register.view.components;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.orderedlayout.*;
import static com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.*;

public class ScheduleSegment extends VerticalLayout{
	public ScheduleSegment(String backgroundColor, String lessonName, String classroomName, String teacherShort){
			 //a very awful workaround in order to fix the component in the ColumnSegment component
			setWidth("148px");
			setHeight("100px");
			getStyle()
					.set("background", backgroundColor)
					.set("color", "black")
					.set("font-size", "13px")
					//.set("border", "1px solid black")
					.set("align-items", "center");
			Label lessonNameLabel = new Label(lessonName);
			HorizontalLayout bottom = new HorizontalLayout();
			Label classroom = new Label(classroomName);
			Label teacherShortLabel = new Label(teacherShort);
			bottom.setWidth("100%");
			bottom.setJustifyContentMode(BETWEEN);
			bottom.add(classroom, teacherShortLabel);
			add(lessonNameLabel, bottom);
	}
}
