package pl.school.register.view.components.dialog;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import pl.school.register.model.Attendance;
import pl.school.register.model.Meeting;
import pl.school.register.model.Student;
import pl.school.register.model.dto.StudentDTO;
import pl.school.register.model.enumerations.AttendanceStatus;
import pl.school.register.service.AttendanceService;
import pl.school.register.service.MeetingService;
import pl.school.register.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingDialog extends Dialog {
    private Meeting meeting;
    private Button confirm, cancel;
    private Tab meetingTab, attendanceTab;
    private VerticalLayout content = new VerticalLayout();
    private StudentService studentService;
    private AttendanceService attendanceService;
    private MeetingService meetingService;
    public MeetingDialog(){
        this.setWidth("500px");
        this.setHeight("500px");
        confirm = new Button("Confirm");
        cancel = new Button("Cancel", e -> this.close());
    }
    private VerticalLayout prepareLayout(){
        VerticalLayout layout = new VerticalLayout();
        meetingTab = new Tab("Meeting");
        attendanceTab = new Tab("Attendance");
        Tabs tabs = new Tabs(meetingTab, attendanceTab);
        tabs.addSelectedChangeListener(listener -> createContent(listener.getSelectedTab()));
        createContent(tabs.getSelectedTab());
        layout.add(tabs, content);
        return layout;
    }

    public void build(){
        add(prepareLayout());
        getFooter().add(cancel, confirm);
    }

    public void createContent(Tab tab){
        content.removeAll();

        if (tab.equals(meetingTab)){
            Label subject = new Label("Subject: " + meeting.getLesson().getSubject().getSubjectName());
            Label schoolClass = new Label("Class: " + meeting.getLesson().getSchoolClass().getClassName());
            TextField topic = new TextField("topic");
            topic.setValue(meeting.getTopic());
            topic.addValueChangeListener(listener -> {
                meeting.setTopic(topic.getValue());
            });
            confirm.addClickListener(listener -> {
                meetingService.addNew(meeting);
                Notification saved = new Notification("Meeting updated");
                saved.setDuration(10);
                saved.open();
                this.close();
            });
            content.add(subject, schoolClass, topic);
        }else {
            Grid<StudentDTO> grid = new Grid<>();
            List<Student> students = studentService.getAllBySchoolClassId(meeting.getLesson().getSchoolClass().getId());
            List<Attendance> att = attendanceService.getAllByMeetingId(meeting.getId());
            List<Attendance> attendances;
            if (att.isEmpty()) {
                attendances = new ArrayList<>();
                students.forEach(student -> {
                    Attendance attendance = new Attendance();
                    attendance.setStudent(student);
                    attendance.setMeeting(meeting);
                    attendances.add(student.getIndex().intValue() - 1, attendance);
                });
            }else{
                attendances = att;
            }
            grid.addColumn(StudentDTO::getIndex).setHeader("Index");
            grid.addColumn(new ComponentRenderer<>(Label::new, (label, student) -> {
                label.setText(String.format("%s %s", student.getFirstName(), student.getLastName()));
            })).setHeader("Name");
            grid.addColumn(new ComponentRenderer<>(Checkbox::new, (checkbox, student) -> {
                checkbox.setValue(attendances.get(student.getIndex().intValue() - 1).getAttendanceStatus() == AttendanceStatus.ATTENDED);
                checkbox.addValueChangeListener(listener -> {
                    attendances.get(student.getIndex().intValue() - 1).setAttendanceStatus(checkbox.getValue() ?
                            AttendanceStatus.ATTENDED : AttendanceStatus.NOT_ATTENDED);
                });
            }));

            grid.setItems(students.stream().map(StudentDTO::new).collect(Collectors.toList()));
            Button save = new Button("Save");
            save.addClickListener(listener -> {
                attendanceService.addAll(attendances);
                Notification notification = new Notification("Attendance saved");
                notification.setDuration(10);
                notification.open();
            });
            content.add(save);
            content.add(grid);
        }
    }

    public void setMeetingService(MeetingService meetingService){
        this.meetingService = meetingService;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public void setStudentService(StudentService studentService){
        this.studentService = studentService;
    }

    public void setAttendanceService(AttendanceService attendanceService){
        this.attendanceService = attendanceService;
    }
}
