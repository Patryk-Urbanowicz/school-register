package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Attendance;
import pl.school.register.model.enumerations.AttendanceStatus;

@Data
@NoArgsConstructor
public class AttendanceDTO {
    private Long id;
    private Long studentId;
    private Long meetingId;
    private AttendanceStatus attendanceStatus;

    public AttendanceDTO(Attendance attendance){
        this.id = attendance.getId();
        this.studentId = attendance.getStudent().getId();
        this.meetingId = attendance.getMeeting().getId();
        this.attendanceStatus = attendance.getAttendanceStatus();
    }

}
