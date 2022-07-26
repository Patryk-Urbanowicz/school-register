package pl.school.register.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
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
    private AttendanceService attendanceService;

    private StudentDTO studentDTO;
    public StudentScheduleView(StudentService studentService, MeetingService meetingService, AttendanceService attendanceService){
        this.studentService = studentService;
        this.meetingService = meetingService;
        this.attendanceService = attendanceService;
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        Student student = studentService.getByLogin(userDetails.getUsername());
        studentDTO = new StudentDTO(student);
        setSizeFull();
        drawLayout(studentDTO);

    }

    private void drawLayout(StudentDTO studentDTO){
        removeAll();

        ScheduleTable st = new ScheduleTable(meetingService, studentService, attendanceService, false, studentDTO, null, null, null);
        ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(st);
        add(wrapper);
    }
}
