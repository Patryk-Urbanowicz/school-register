package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.Mark;
import pl.school.register.model.Parent;
import pl.school.register.model.Subject;
import pl.school.register.model.dto.StudentDTO;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.*;
import pl.school.register.view.components.MarkTable;
import pl.school.register.view.components.ResponsiveTableWrapper;
import pl.school.register.view.components.ScheduleTable;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

//I'm so good with names
@RolesAllowed(value = {"PARENT"})
@Route(value = "parent", layout = pl.school.register.view.ParentLayout.class)
public class ParentChildrenLayout extends VerticalLayout {
    private StudentService studentService;
    private MeetingService meetingService;
    private MarkService markService;
    private AttendanceService attendanceService;
    Select<StudentDTO> select = new Select<>();
    public ParentChildrenLayout(ParentService parentService, StudentService studentService,
                                MeetingService meetingService,
                                MarkService markService,
                                AttendanceService attendanceService){
        this.studentService = studentService;
        this.meetingService = meetingService;
        this.markService = markService;
        this.attendanceService = attendanceService;
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        Parent parent = parentService.getByLogin(userDetails.getUsername());
        select.setItemLabelGenerator(student -> String.format("%s %s", student.getFirstName(), student.getLastName()));
        List<StudentDTO> children = parent.getChildren().stream().map(StudentDTO::new).collect(Collectors.toList());
        select.setItems(children);
        select.setValue(children.get(0));
        select.addValueChangeListener(listener -> {
            drawLayout(listener.getValue());
        });
        drawLayout(children.get(0));

    }

    private void drawLayout(StudentDTO studentDTO){
        removeAll();
        add(select);
        LocalDate now = LocalDate.now();
        TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
        LocalDate monday = now.with(fieldISO, 1);
        LocalDate friday = now.with(fieldISO, 6);
        List<MeetingInWeek> meetings = meetingService.getWithWeekDayByTSchoolClassId(studentDTO.getSchoolClassId(),monday, friday);
        add(new ResponsiveTableWrapper(new ScheduleTable(meetings, meetingService, studentService, attendanceService, false, studentDTO)));
        List<Mark> marks = markService.getAllByStudentId(studentDTO.getId());
        Map<Subject, List<Mark>> map = marks.stream().collect(Collectors.groupingBy(mark
                -> mark.getLesson().getSubject()));
        add(new ResponsiveTableWrapper(new MarkTable(map)));
    }
}
