package pl.school.register.view.components;

import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.*;

public class ScheduleSegment extends RowSegment {
	public ScheduleSegment(String backgroundColor, String lessonName, String classroomName, String teacherShort){
			setClassName("schedule-segment");
			getStyle().set("background", backgroundColor);
			Label lessonNameLabel = new Label(lessonName);
			HorizontalLayout bottom = new HorizontalLayout();
			Label classroom = new Label(classroomName);
			Label teacherShortLabel = new Label(teacherShort);
			bottom.setClassName("bottom-element");
			bottom.add(classroom, teacherShortLabel);
			add(lessonNameLabel, bottom);
	}
}
