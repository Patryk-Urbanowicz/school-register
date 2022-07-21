package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.school.register.service.StudentService;

@Route(value = "teacher/class/:classId/subject", layout = TeacherLayout.class)
public class TeacherSchoolClassStudentListView extends VerticalLayout {
    private StudentService studentService;
    public TeacherSchoolClassStudentListView(StudentService studentService){
        this.studentService = studentService;
    }
    //TODO: Later
}
