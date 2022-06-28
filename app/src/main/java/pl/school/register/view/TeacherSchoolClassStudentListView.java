package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;

import java.util.Optional;

@Route(value = "teacher/class/:classId/students", layout = TeacherLayout.class)
public class TeacherSchoolClassStudentListView extends VerticalLayout implements BeforeEnterObserver {
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteParameters parameters = beforeEnterEvent.getRouteParameters();
        Optional<String> classIdOptional = parameters.get("classId");
        if (classIdOptional.isPresent()){
            String classId = classIdOptional.get();
            System.out.println(classId);
            //TODO: implement fetching students from database when repositories will be ready

        }
    }
}
