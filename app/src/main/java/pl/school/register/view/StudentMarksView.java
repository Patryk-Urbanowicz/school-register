package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.school.register.model.*;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.service.MarkService;
import pl.school.register.service.StudentService;
import pl.school.register.view.components.MarkTable;
import pl.school.register.view.components.ResponsiveTableWrapper;

import javax.annotation.security.RolesAllowed;
import java.util.*;
import java.util.stream.Collectors;

@RolesAllowed(value = {"STUDENT"})
@Route(value = "account/marks", layout = AccountMiddlePanelNestedLayout.class)
public class StudentMarksView extends VerticalLayout {
    Student student1 = new Student();
    private StudentService studentService;
    private MarkService markService;
    public StudentMarksView(StudentService studentService, MarkService markService){
        this.studentService = studentService;
        this.markService = markService;
        UserDetails userDetails = (UserDetails) ((UsernamePasswordAuthenticationToken) SecurityContextHolder
                .getContext().getAuthentication()).getPrincipal();
        Student student = studentService.getByLogin(userDetails.getUsername());
        List<Mark> marks = markService.getAllByStudentId(student.getId());
        Map<Subject, List<Mark>> map = marks.stream().collect(Collectors.groupingBy(mark
                -> mark.getLesson().getSubject()));
        MarkTable mt = new MarkTable(map);
        ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(mt);
        add(wrapper);
    }
}
