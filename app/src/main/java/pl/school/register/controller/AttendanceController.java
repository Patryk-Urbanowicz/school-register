package pl.school.register.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.school.register.model.*;
import pl.school.register.model.dto.AttendanceDTO;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.model.enumerations.AttendanceStatus;
import pl.school.register.service.AttendanceService;
import pl.school.register.service.StudentService;
import pl.school.register.service.TeacherService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;
    private StudentService studentService;
    private TeacherService teacherService;

    public AttendanceController(AttendanceService attendanceService, StudentService studentService,
                                TeacherService teacherService) {
        this.attendanceService = attendanceService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addNewAttendance(Authentication auth, @RequestBody AttendanceDTO attendanceDTO){
        if(attendanceDTO.getId() != null) return ResponseEntity.badRequest().build();
        Attendance attendance = attendanceService.mapDTOToModel(attendanceDTO);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher t1 = teacherService.getByLogin(userDetails.getUsername());
        if(t1 == null) return ResponseEntity.badRequest().build();
        if(studentService.getById(attendanceDTO.getStudentId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Attendance newAttendance = attendanceService.addNew(attendance);
        return ResponseEntity.ok(newAttendance.getId());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateAttendance(Authentication auth, @RequestBody AttendanceDTO attendanceDTO){
        if(attendanceDTO.getId() == null) return ResponseEntity.badRequest().build();
        if(attendanceService.getById(attendanceDTO.getStudentId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Attendance> test = attendanceService.getById(attendanceDTO.getId());
        if(test.isEmpty()) return ResponseEntity.badRequest().build();
        Attendance attendance = test.get();
        if(attendanceDTO.getAttendanceStatus() != null) attendance.setAttendanceStatus(attendanceDTO.getAttendanceStatus());
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        attendanceService.addNew(attendance);
        return ResponseEntity.ok(attendance.getId());
    }

    @GetMapping("/student/{login}")
    public ResponseEntity<Map<String, Float>> findAttendancesByLogin(@PathVariable("login") String login){
        Student student = studentService.getByLogin(login);
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        List<Lesson> l1 = new ArrayList<>(student.getSchoolClass().getLessons());
        Map<String, Float> m1 = new HashMap<>();
        for(Lesson l : l1){
            List<Attendance> list = attendanceService.getAllAttendancesByLessonIdAndStudentId(l.getId(), student.getId());
            float s = list.size();
            float o = list.stream().filter(a -> a.getAttendanceStatus().equals(AttendanceStatus.ATTENDED)).count();
            m1.put(l.getSubject().getSubjectName(), o/s);
        }
        return ResponseEntity.ok(m1);
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Float>> findAttendancesByLogin(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Student student = studentService.getByLogin(userDetails.getUsername());
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        List<Lesson> l1 = new ArrayList<>(student.getSchoolClass().getLessons());
        Map<String, Float> m1 = new HashMap<>();
        for(Lesson l : l1){
            List<Attendance> list = attendanceService.getAllAttendancesByLessonIdAndStudentId(l.getId(), student.getId());
            float s = list.size();
            float o = list.stream().filter(a -> a.getAttendanceStatus().equals(AttendanceStatus.ATTENDED)).count();
            m1.put(l.getSubject().getSubjectName(), o/s);
        }
        return ResponseEntity.ok(m1);
    }
}
