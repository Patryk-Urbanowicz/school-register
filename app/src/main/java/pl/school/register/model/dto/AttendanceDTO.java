package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import pl.school.register.model.*;
import pl.school.register.model.dto.mapper.MappingUtils;
import pl.school.register.model.enumerations.AttendanceStatus;

@Data
@NoArgsConstructor
public class AttendanceDTO implements DTO {
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

    @Override
    public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
        mapper.addMappings(attendanceMap(utils));
        return mapper;
    }

    public PropertyMap<AttendanceDTO, Attendance> attendanceMap(MappingUtils utils) {
        return new PropertyMap<AttendanceDTO, Attendance>() {
            @Override
            protected void configure() {
                Converter<AttendanceDTO, Attendance> converter = new AbstractConverter<AttendanceDTO, Attendance>() {
                    @Override
                    protected Attendance convert(AttendanceDTO attendanceDTO) {
                        Attendance attendance = new Attendance();
                        Student student = new Student();
                        Meeting meeting = new Meeting();
                        student.setId(attendanceDTO.getStudentId());
                        meeting.setId(attendanceDTO.getMeetingId());
                        attendance.setAttendanceStatus(attendanceDTO.getAttendanceStatus());
                        attendance.setStudent(student);
                        attendance.setMeeting(meeting);
                        return attendance;
                    }
                };
            }
        };
    };
}
