package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Attendance;
import pl.school.register.model.Mark;
import pl.school.register.model.dto.AttendanceDTO;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.model.dto.mapper.MappingUtils;
import pl.school.register.repositories.AttendanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final MappingUtils mappingUtils;

    public AttendanceService(AttendanceRepository attendanceRepository, MappingUtils mappingUtils) {
        this.attendanceRepository = attendanceRepository;
        this.mappingUtils = mappingUtils;
    }

    public Attendance addNew(Attendance attendance){
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAll(){
         return attendanceRepository.findAll();
    }

    public Optional<Attendance> getById(Long id){
        return attendanceRepository.findById(id);
    }

    public List<Attendance> getAllByStudentId(Long id){
        return attendanceRepository.findAllByStudentId(id);
    }

    public List<Attendance> getAllByLessonId(Long id){
        return attendanceRepository.findAllByMeetingId(id);
    }

    public List<Attendance> getAllByMeetingId(Long id){
        return attendanceRepository.findAllByMeetingId(id);
    }

    public void addAll(List<Attendance> attendances) {
        attendanceRepository.saveAll(attendances);
    }

    public List<Attendance> getAllAttendancesByLessonIdAndStudentId(Long lessonId, Long studentId){
        return attendanceRepository.findAttendanceByLessonIdAndStudentId(lessonId, studentId);
    }

    public Attendance getByMeetingIdAndStudentId(Long meeting_id, Long student_id){
        return attendanceRepository.findByMeetingIdAndStudentId(meeting_id, student_id);
    }

    public Attendance mapDTOToModel(AttendanceDTO attendanceDTO){
        return mappingUtils.mapFromDTO(attendanceDTO, Attendance.class);
    }
}
