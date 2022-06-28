package pl.school.register.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import pl.school.register.model.Lesson;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Subject;
import pl.school.register.model.Teacher;
import pl.school.register.view.components.TeacherNavBar;

import java.util.*;

@Route(value = "teacher")
@CssImport(value = "./themes/accordion.css", themeFor = "vaadin-accordion-panel")
public class TeacherLayout extends AppLayout {
    ArrayList<Lesson> lessons = new ArrayList<>();
    ArrayList<SchoolClass> classes = new ArrayList<>();
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
        classes.forEach(_class -> {
            Accordion accordion = new Accordion();
            accordion.setClassName("accordion");
            VerticalLayout accordionLinks = new VerticalLayout();
            RouterLink listOfStudents = new RouterLink("Students",
                                    TeacherSchoolClassStudentListView.class,
                                    new RouteParameters(Map.of("classId", _class.getId().toString())));
            accordionLinks.add(listOfStudents);
            _class.getLessons().forEach(lesson -> {
                Map<String, String> params = new HashMap<>();
                params.put("classId", lesson.getSchoolClass().getId().toString());
                params.put("subjectId", lesson.getSubject().getId().toString());
                RouterLink link = new RouterLink(lesson.getSubject().getSubjectName(),
                        TeacherSchoolClassView.class,
                        new RouteParameters(params));
                accordionLinks.add(link);
            });
            accordion.add(_class.getClassName(), accordionLinks);
            links.add(accordion);
        });
        return links;
    }

    private void sampleData() {
        Subject przyra = new Subject();
        przyra.setId(1L);
        przyra.setSubjectName("pszyra");

        Subject historia = new Subject();
        historia.setId(2L);
        historia.setSubjectName("historia");

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

        Lesson historia1k = new Lesson();
        historia1k.setSchoolClass(_1k);
        historia1k.setTeacher(teacher);
        historia1k.setSubject(historia);

        Lesson przyra3t = new Lesson();
        przyra3t.setSchoolClass(_3t);
        przyra3t.setTeacher(teacher);
        przyra3t.setSubject(przyra);

        Lesson przyra2a = new Lesson();
        przyra2a.setSchoolClass(_2a);
        przyra2a.setTeacher(teacher);
        przyra2a.setSubject(przyra);
        lessons.addAll(List.of(przyra1k, przyra3t, przyra2a, historia1k));
        _1k.setLessons(Set.of(przyra1k, historia1k));
        _2a.setLessons(Set.of(przyra2a));
        _3t.setLessons(Set.of(przyra3t));
        classes.addAll(List.of(_1k, _2a, _3t));
    }
}
