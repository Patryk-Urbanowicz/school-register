package pl.school.register.view.components;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.orderedlayout.*;
import java.util.*;
import pl.school.register.view.components.*;

public class ScheduleTable extends HorizontalLayout{
	public ScheduleTable(){
		setSpacing(false);
		getStyle()
				.set("border-style", "none none none solid")
				.set("border-width", "1px")
				.set("border-color", "black");
		List<String> weekdays = List.of("monday","tuesday","wednesday","thursday","friday");
		weekdays.stream()
				.forEach(weekday -> {
					add(new Column(weekday, 8));
				});
	}
}
