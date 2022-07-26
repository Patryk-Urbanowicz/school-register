package pl.school.register.view;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteParameters;
import com.vaadin.flow.router.RouterLink;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Subject;
import pl.school.register.model.Teacher;
import pl.school.register.service.SchoolClassService;
import pl.school.register.service.TeacherService;
import pl.school.register.view.components.NavBar;

import javax.annotation.security.RolesAllowed;
import java.util.*;

@Route(value = "teacher")
@RolesAllowed(value = {"TEACHER"})
@CssImport(value = "./themes/accordion.css", themeFor = "vaadin-accordion-panel")
public class TeacherLayout extends AppLayout {
    private SchoolClassService schoolClassService;
    private TeacherService teacherService;
    public TeacherLayout(SchoolClassService schoolClassService, TeacherService teacherService) {
        this.schoolClassService = schoolClassService;
        this.teacherService = teacherService;
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());
        addToDrawer(getDrawer());
        addToNavbar(new NavBar(teacher));
    }

    private VerticalLayout getDrawer() {
        VerticalLayout links = new VerticalLayout();
        links.setHeightFull();
        links.setClassName("teacher-drawer");
        List<SchoolClass> classes = schoolClassService.getAllByTeacherWhoHasLessonsWith(1L);
        classes.forEach(_class -> {
            Accordion accordion = new Accordion();
            accordion.setClassName("accordion");
            VerticalLayout accordionLinks = new VerticalLayout();
            _class.getLessons().forEach(lesson -> {
                Accordion markScheduleAccordion = new Accordion();
                VerticalLayout markSchedulePair = new VerticalLayout();
                Map<String, String> params = new HashMap<>();
                Subject subject = lesson.getSubject();
                params.put("classId", lesson.getSchoolClass().getId().toString());
                params.put("lessonId", lesson.getId().toString());
                RouterLink schedule = new RouterLink("Meetings",
                        TeacherSchoolClassView.class,
                        new RouteParameters(params));
                RouterLink marks = new RouterLink("Marks",
                        TeacherSchoolClassMarkListView.class,
                        new RouteParameters(params));
                markSchedulePair.add(schedule, marks);
                markScheduleAccordion.add(subject.getSubjectName(), markSchedulePair);
                accordionLinks.add(markScheduleAccordion);
            });
            accordion.add(_class.getClassName(), accordionLinks);
            links.add(accordion);
        });
        return links;
    }
}
