package pl.school.register.view.components.dialog;


import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.school.register.model.Meeting;
import pl.school.register.model.Subject;
import pl.school.register.model.Teacher;
import pl.school.register.model.enumerations.AttendanceStatus;


public class MeetingInfoDialog extends Dialog {
    private Meeting meeting;
    private AttendanceStatus attendanceStatus;
    public MeetingInfoDialog(Meeting meeting){
        this.setWidth("500px");
        this.setHeight("500px");
        this.meeting = meeting;
    }
    private VerticalLayout prepareLayout(){
        VerticalLayout layout = new VerticalLayout();
        Teacher teacher = meeting.getTeacher();
        Subject subject = meeting.getLesson().getSubject();
        Label teacherLabel = new Label();
        teacherLabel.setText(String.format("Teacher: %s %s", teacher.getFirstName(), teacher.getLastName()));
        Label meetingTopic = new Label();
        meetingTopic.setText(String.format("Topic: %s", meeting.getTopic() != null ? meeting.getTopic() : ""));
        Label attendanceStatusLabel = new Label();
        Label subjectLabel = new Label();
        subjectLabel.setText(String.format("Subject: %s", subject.getSubjectName() != null ? subject.getSubjectName() : ""));
        Label descriptionLabel = new Label();
        descriptionLabel.setText(String.format("Description: %s", meeting.getDescription() != null ? meeting.getDescription() : ""));
        if (attendanceStatus == null){
            attendanceStatusLabel.setText("Meeting hasn't happened yet");
        }else if (attendanceStatus.equals(AttendanceStatus.ATTENDED)){
            attendanceStatusLabel.setText("ATTENDED");
        }else if (attendanceStatus.equals(AttendanceStatus.NOT_ATTENDED)){
            attendanceStatusLabel.setText("ABSENT");
        }

        layout.add(teacherLabel, meetingTopic, subjectLabel, descriptionLabel, attendanceStatusLabel);
        return layout;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public void build(){
        add(prepareLayout());
    }
}
