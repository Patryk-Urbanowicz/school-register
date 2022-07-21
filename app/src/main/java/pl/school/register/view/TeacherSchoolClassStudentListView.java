package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import pl.school.register.model.*;
import pl.school.register.service.TeacherService;
import pl.school.register.view.components.StudentRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Route(value = "teacher/class/:classId/students", layout = TeacherLayout.class)
public class TeacherSchoolClassStudentListView extends VerticalLayout implements BeforeEnterObserver {
    private TeacherService teacherService;
    public TeacherSchoolClassStudentListView(TeacherService teacherService){
        this.teacherService = teacherService;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteParameters parameters = beforeEnterEvent.getRouteParameters();
        Optional<String> classIdOptional = parameters.get("classId");
        //TODO: change when merged with kkocieniewski
        Teacher teacher = teacherService.getById(2L).get();
        List<String> labels = teacher.getMarks()
                .stream()
                .filter( distinctByKey(Mark::getLabel) )
                .map(Mark::getLabel)
                .collect(Collectors.toList());
        if (classIdOptional.isPresent()){
            Long classId = Long.parseLong(classIdOptional.get());
        }
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
