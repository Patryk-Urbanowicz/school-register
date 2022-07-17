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
import pl.school.register.repositories.SchoolClassRepository;
import pl.school.register.service.SchoolClassService;
import pl.school.register.view.components.TeacherNavBar;

import java.util.*;

//@Route(value = "teacher")
//@CssImport(value = "./themes/accordion.css", themeFor = "vaadin-accordion-panel")
//public class TeacherLayout extends AppLayout {
//    private SchoolClassService schoolClassService;
//    public TeacherLayout(SchoolClassService schoolClassService) {
//        this.schoolClassService = schoolClassService;
//        Teacher teacher = new Teacher();
//        teacher.setFirstName("aaa");
//        teacher.setLastName("aAAAAA");
//        addToDrawer(getDrawer());
//        addToNavbar(new TeacherNavBar(teacher));
//    }
//
//    private VerticalLayout getDrawer() {
//        VerticalLayout links = new VerticalLayout();
//        links.setHeightFull();
//        links.setClassName("teacher-drawer");
//        List<SchoolClass> classes = schoolClassService.getAllByTeacherWhoHasLessonsWith(1L);
//        classes.forEach(_class -> {
//            Accordion accordion = new Accordion();
//            accordion.setClassName("accordion");
//            VerticalLayout accordionLinks = new VerticalLayout();
//            RouterLink listOfStudents = new RouterLink("Students",
//                                    TeacherSchoolClassStudentListView.class,
//                                    new RouteParameters(Map.of("classId", _class.getId().toString())));
//            accordionLinks.add(listOfStudents);
//            _class.getLessons().forEach(lesson -> {
//                Map<String, String> params = new HashMap<>();
//                params.put("classId", lesson.getSchoolClass().getId().toString());
//                params.put("subjectId", lesson.getSubject().getId().toString());
//                RouterLink link = new RouterLink(lesson.getSubject().getSubjectName(),
//                        TeacherSchoolClassView.class,
//                        new RouteParameters(params));
//                accordionLinks.add(link);
//            });
//            accordion.add(_class.getClassName(), accordionLinks);
//            links.add(accordion);
//        });
//        return links;
//    }
//}
