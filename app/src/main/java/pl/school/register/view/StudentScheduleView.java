package pl.school.register.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.*;
import pl.school.register.model.dto.StudentDTO;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.AttendanceService;
import pl.school.register.service.LessonBlockService;
import pl.school.register.service.MeetingService;
import pl.school.register.service.StudentService;
import pl.school.register.view.components.*;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

@RolesAllowed(value = {"STUDENT"})
@Route(value = "student/schedule", layout = AccountMiddlePanelNestedLayout.class)
public class StudentScheduleView extends VerticalLayout {
    private StudentService studentService;
    private MeetingService meetingService;
    public StudentScheduleView(StudentService studentService, MeetingService meetingService, AttendanceService attendanceService){
        this.studentService = studentService;
        this.meetingService = meetingService;
        setSizeFull();
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
         Student student = studentService.getByLogin(userDetails.getUsername());

        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate monday = now.with(fieldISO, 1);
        LocalDate friday = now.with(fieldISO, 6);
        List<MeetingInWeek> meetings = meetingService.getWithWeekDayByTSchoolClassId(
                student.getSchoolClass().getId(),
                monday,
                friday);
        ScheduleTable st = new ScheduleTable(meetings, meetingService, studentService, attendanceService, false, new StudentDTO(student));
        ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(st);
		add(wrapper);
    }
}
