package pl.school.register.view.components;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.school.register.model.Mark;
import pl.school.register.model.Teacher;

public class MarkInfoComponent extends VerticalLayout {
    public MarkInfoComponent(Mark mark){
        setClassName("hide");
        setSizeUndefined();
        Label label = new Label(String.format("Label: %s", mark.getLabel()));
        Label description = new Label(String.format("Description: %s", mark.getDescription() != null ? mark.getDescription() : "" ));
        Label value = new Label(String.format("Mark: %d", mark.getValue()));
        Label weight = new Label(String.format("Weight: %d", mark.getWeight()));
        Label subject = new Label(String.format("Subject: %s", mark.getLesson().getSubject().getSubjectName()));
        Label byWhom = new Label(String.format("Teacher: %s", getTeacherFirstAndLastName(mark.getLesson().getTeacher())));

        add(label, description, value, weight, subject, byWhom);
    }

    private String getTeacherFirstAndLastName(Teacher teacher){
        return String.format("%s %s", teacher.getFirstName(), teacher.getLastName());
    }
}
