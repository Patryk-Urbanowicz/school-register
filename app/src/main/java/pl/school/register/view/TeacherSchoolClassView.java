package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Student;
import pl.school.register.view.components.ScheduleTable;

import java.util.*;

@Route(value = "teacher/class/:classId/subject/:subjectId", layout = TeacherLayout.class)
public class TeacherSchoolClassView extends VerticalLayout implements BeforeEnterObserver {
    private SchoolClass schoolClass;
    public TeacherSchoolClassView(){
        setClassName("school-class-view");
        removeAll();
        sampleData();
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
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        removeAll(); //A very bad way of "refreshing" components on page

        RouteParameters params = beforeEnterEvent.getRouteParameters();
        Optional<String> subjectId = params.get("subjectId");
        Optional<String> classId = params.get("classId");
        if (subjectId.isPresent() && classId.isPresent()){
            String subjectIdString = subjectId.get();
            String classIdString = classId.get();
            add(new H1("Class: " + classIdString +  " Subject: " + subjectIdString ));
            add(new ScheduleTable(Collections.EMPTY_LIST));
        }

    }
}
