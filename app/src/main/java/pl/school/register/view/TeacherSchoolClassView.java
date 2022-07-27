package pl.school.register.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.*;
import pl.school.register.model.dto.TeacherDTO;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.*;
import pl.school.register.view.components.ResponsiveTableWrapper;
import pl.school.register.view.components.ScheduleTable;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@RolesAllowed(value = {"TEACHER"})
@Route(value = "teacher/class/:classId/lesson/:lessonId/schedule", layout = TeacherLayout.class)
public class TeacherSchoolClassView extends VerticalLayout implements BeforeEnterObserver {
    private MeetingService meetingService;
    private TeacherService teacherService;
    private LessonService lessonService;
    private StudentService studentService;
    private AttendanceService attendanceService;
    private Teacher teacher;
    private Long lessonIdL , classIdL;
    public TeacherSchoolClassView(MeetingService meetingService,
                                  TeacherService teacherService,
                                  StudentService studentService,
                                  AttendanceService attendanceService,
                                  LessonService lessonService){
        this.meetingService = meetingService;
        this.studentService = studentService;
        this.attendanceService = attendanceService;
        this.lessonService = lessonService;
        setClassName("middle-panel");
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        teacher = teacherService.getByLogin(userDetails.getUsername());
        setClassName("school-class-view");
        removeAll();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        removeAll(); //A very bad way of "refreshing" components on page
        RouteParameters params = beforeEnterEvent.getRouteParameters();
        Optional<String> lessonId = params.get("lessonId");
        Optional<String> classId = params.get("classId");
        if (lessonId.isPresent() && classId.isPresent()){

            lessonIdL = Long.parseLong(lessonId.get());
            classIdL = Long.parseLong(classId.get());
            Optional<Lesson> lesson = lessonService.getById(lessonIdL);
            lesson.ifPresent(value -> {
                add(new H1("Class: " + value.getSchoolClass().getClassName()),
                        new H1("Lesson: " + value.getSubject().getSubjectName() ));
            });
            ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(new ScheduleTable(meetingService, studentService,
                    attendanceService, true, null, new TeacherDTO(teacher), classIdL, lessonIdL));
            wrapper.getStyle().set("padding", "30px")
                            .set("background", "#efefef")
                                    .set("width", "95%")
                                            .set("display", "flex")
                                                    .set("justify-content", "center");
            add(wrapper);
        }

    }
}
