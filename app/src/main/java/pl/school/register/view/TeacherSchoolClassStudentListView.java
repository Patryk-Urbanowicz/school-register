package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import pl.school.register.model.*;
import pl.school.register.service.Student_ClassService;
import pl.school.register.service.TeacherService;
import pl.school.register.view.components.StudentRow;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Route(value = "teacher/class/:classId/students", layout = TeacherLayout.class)
public class TeacherSchoolClassStudentListView extends VerticalLayout implements BeforeEnterObserver {
    private Student_ClassService student_classService;
    private TeacherService teacherService;
    public TeacherSchoolClassStudentListView(Student_ClassService student_classService, TeacherService teacherService){
        this.student_classService = student_classService;
        this.teacherService = teacherService;
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteParameters parameters = beforeEnterEvent.getRouteParameters();
        Optional<String> classIdOptional = parameters.get("classId");
        //TODO: change when merged with kkocieniewski
        Teacher teacher = teacherService.getTeacherById(1L).get();
        List<String> labels = teacher.getMarks()
                .stream()
                .filter( distinctByKey(Mark::getLabel) )
                .map(Mark::getLabel)
                .collect(Collectors.toList());
        if (classIdOptional.isPresent()){
            Long classId = Long.parseLong(classIdOptional.get());
            List<Student_Class> student_classes = student_classService.getAllBySchoolClassId(classId);
            student_classes.forEach(student -> add(new StudentRow(student.getStudent(), labels, student.getIndex())));
        }
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
