package pl.school.register.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import pl.school.register.model.Account;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Student;
import pl.school.register.view.components.ScheduleTable;

import java.util.*;

@Route(value = "teacher/class", layout = TeacherLayout.class)
public class TeacherSchoolClassView extends VerticalLayout implements HasUrlParameter<String> {
    private SchoolClass schoolClass;
    public TeacherSchoolClassView(){
        setClassName("school-class-view");
        sampleData();
        add(new ScheduleTable(Collections.EMPTY_LIST));
    }

    private void sampleData(){
        schoolClass = new SchoolClass();
        Student s1 = new Student();
        s1.setFirstName("a");
        Student s2 = new Student();
        s2.setFirstName("b");
        Student s3 = new Student();
        s3.setFirstName("c");
        schoolClass.setStudents(Set.of(s1, s2, s3));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        System.out.println(s);
    }
}
