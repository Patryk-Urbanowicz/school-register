package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Attendance;
import pl.school.register.repositories.AttendanceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public void addAttendance(@RequestBody Attendance attendance){
        attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendances(){
         return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Long id){
        return attendanceRepository.findById(id);
    }

    public List<Attendance> getAttendancesByStudentId(Long id){
        return attendanceRepository.findAttendancesByStudentId(id);
    }

    public List<Attendance> getAttendancesByLessonId(Long id){
        return attendanceRepository.findAttendancesByLessonId(id);
    }
}
