package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.*;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.AttendanceService;
import pl.school.register.service.MeetingService;
import pl.school.register.service.StudentService;
import pl.school.register.service.TeacherService;
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
    private StudentService studentService;
    private AttendanceService attendanceService;
    private Teacher teacher;
    public TeacherSchoolClassView(MeetingService meetingService, TeacherService teacherService,
                                  StudentService studentService, AttendanceService attendanceService){
        this.meetingService = meetingService;
        this.studentService = studentService;
        this.attendanceService = attendanceService;
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
            LocalDate now = LocalDate.now();
            TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();

            LocalDate monday = now.with(fieldISO, 1);
            LocalDate friday = now.with(fieldISO, 6);
            System.out.println(monday);
            System.out.println(friday);

            Long lessonIdL = Long.parseLong(lessonId.get());
            Long classIdL = Long.parseLong(classId.get());
            List<MeetingInWeek> meetingsInWeek = meetingService
                    .getWithWeekDayByTeacherIdAndSchoolClassId(teacher.getId(), classIdL, lessonIdL,
                                                                monday, friday);
            add(new H1("Class: " + classIdL +  " Lesson: " + lessonIdL ));
            add(new ResponsiveTableWrapper(new ScheduleTable(meetingsInWeek, meetingService, studentService, attendanceService, true)));
        }

    }
}
