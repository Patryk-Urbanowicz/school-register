package pl.school.register.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import pl.school.register.model.Lesson;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Subject;
import pl.school.register.model.Teacher;
import pl.school.register.view.components.NavBar;
import pl.school.register.view.components.TeacherNavBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Route(value = "teacher")
@CssImport(value = "./themes/accordion.css", themeFor = "vaadin-accordion-panel")
public class TeacherLayout extends AppLayout {
    ArrayList<Lesson> lessons = new ArrayList<>();
    Teacher teacher;
    public TeacherLayout() {
        addToDrawer(getDrawer());
        addToNavbar(new TeacherNavBar(teacher));
    }

    private VerticalLayout getDrawer() {
        VerticalLayout links = new VerticalLayout();
        links.setHeightFull();
        links.setClassName("teacher-drawer");
        sampleData();
        lessons.forEach(lesson -> {
            Accordion accordion = new Accordion();
            RouterLink link = new RouterLink(lesson.getSubject().getSubjectName(), TeacherSchoolClassView.class, lesson.getSchoolClass().getId().toString());
            QueryParameters parameters = QueryParameters.simple(Map.of("subject", lesson.getSubject().getId().toString()));
            link.setQueryParameters(parameters);
            accordion.setClassName("accordion");
            accordion.add(lesson.getSchoolClass().getClassName(), link);
            links.add(accordion);
        });

        return links;
    }

    private void sampleData() {
        Subject przyra = new Subject();
        przyra.setId(1L);
        przyra.setSubjectName("pszyra");
        teacher = new Teacher();
        teacher.setFirstName("Joe");
        teacher.setLastName("Bin-laden");

        SchoolClass _1k = new SchoolClass();
        _1k.setId(1L);
        _1k.setClassName("1k");
        _1k.setProfile("humanistyczny");

        SchoolClass _3t = new SchoolClass();
        _3t.setId(3L);
        _3t.setClassName("3t");
        _3t.setProfile("matematyczny");

        SchoolClass _2a = new SchoolClass();
        _2a.setId(2L);
        _2a.setClassName("2a");
        _2a.setProfile("pszyrodniczy");

        Lesson przyra1k = new Lesson();
        przyra1k.setSchoolClass(_1k);
        przyra1k.setTeacher(teacher);
        przyra1k.setSubject(przyra);

        Lesson przyra3t = new Lesson();
        przyra3t.setSchoolClass(_3t);
        przyra3t.setTeacher(teacher);
        przyra3t.setSubject(przyra);

        Lesson przyra2a = new Lesson();
        przyra2a.setSchoolClass(_2a);
        przyra2a.setTeacher(teacher);
        przyra2a.setSubject(przyra);
        lessons.addAll(List.of(przyra1k, przyra3t, przyra2a));
        lessons.forEach(l -> {
            System.out.println(l.getSubject().getSubjectName());
            System.out.println(l.getSchoolClass().getClassName());
        });

    }
}
