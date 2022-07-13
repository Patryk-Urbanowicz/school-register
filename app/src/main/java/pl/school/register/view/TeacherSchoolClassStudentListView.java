package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import pl.school.register.model.Mark;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Student;
import pl.school.register.model.Teacher;
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
    private SchoolClass schoolClass;
    private Teacher teacher;
    public TeacherSchoolClassStudentListView(){
        sampleData();
    }
    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        RouteParameters parameters = beforeEnterEvent.getRouteParameters();
        Optional<String> classIdOptional = parameters.get("classId");
        List<String> labels = teacher.getMarks()
                .stream()
                .filter( distinctByKey(Mark::getLabel) )
                .map(Mark::getLabel)
                .collect(Collectors.toList());
        if (classIdOptional.isPresent()){
            String classId = classIdOptional.get();
            //TODO: implement fetching students from database when repositories will be ready
            schoolClass.getStudents().forEach(student -> {
                add(new StudentRow(student, labels));
            });
        }
    }

    private <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor){
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private void sampleData(){
        schoolClass = new SchoolClass();
        schoolClass.setClassName("1T");

        teacher = new Teacher();


        Student s1 = new Student();
        s1.setFirstName("A");
        s1.setLastName("AA");
        s1.setSchoolClass(schoolClass);

        Student s2 = new Student();
        s2.setFirstName("B");
        s2.setLastName("BB");
        s2.setSchoolClass(schoolClass);

        Student s3 = new Student();
        s3.setFirstName("C");
        s3.setLastName("CC");
        s3.setSchoolClass(schoolClass);

        Mark p = new Mark();
        p.setLabel("label1");
        p.setValue(1);
        p.setStudent(s1);
        p.setWeight(4);

        Mark bdb = new Mark();
        bdb.setLabel("label2");
        bdb.setValue(5);
        bdb.setStudent(s2);
        bdb.setWeight(2);

        s1.setMarks(Set.of(p));
        s2.setMarks(Set.of(p, bdb));
        schoolClass.setStudents(Set.of(s1 ,s2, s3));

        teacher.setMarks(Set.of(p, bdb));
    }

}
