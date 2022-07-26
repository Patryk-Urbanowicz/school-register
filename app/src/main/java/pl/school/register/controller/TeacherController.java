package pl.school.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Mark;
import pl.school.register.model.Teacher;
import pl.school.register.model.dto.LessonBlockDTO;
import pl.school.register.model.dto.LessonDTO;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.model.enumerations.Role;
import pl.school.register.model.projections.AccountInfo;
import pl.school.register.repositories.MarkRepository;
import pl.school.register.service.AccountService;
import pl.school.register.service.LessonBlockService;
import pl.school.register.service.LessonService;
import pl.school.register.service.TeacherService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private TeacherService teacherService;
    private AccountService accountService;
    private LessonBlockService lessonBlockService;
    private LessonService lessonService;
    private MarkRepository markRepository;

    public TeacherController(TeacherService teacherService, AccountService accountService, LessonBlockService lessonBlockService, LessonService lessonService, MarkRepository markRepository) {
        this.teacherService = teacherService;
        this.accountService = accountService;
        this.lessonBlockService = lessonBlockService;
        this.lessonService = lessonService;
        this.markRepository = markRepository;
    }

    @GetMapping("/")
    public ResponseEntity<AccountInfo> findByLogin(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        AccountInfo accountInfo = accountService.getInfoByLogin(userDetails.getUsername());

        if(accountInfo == null) return ResponseEntity.status(400).build();
        if(!accountInfo.getRole().equals(Role.ROLE_TEACHER)) return ResponseEntity.status(403).build();
        return ResponseEntity.ok(accountInfo);
    }

    @GetMapping("/lessons")
    public ResponseEntity<List<LessonDTO>> findLessonsByLogin(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());

        if (teacher == null) return ResponseEntity.status(400).build();
        List<Lesson> lessons = lessonService.getAllByTeacherId(teacher.getId());
        List<LessonDTO> lessonDTOS = lessons.stream().map(LessonDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(lessonDTOS);
    }

    @GetMapping("/schedule")
    public ResponseEntity<List<LessonBlockDTO>> findLessonBlocksByLogin(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());

        if (teacher == null) return ResponseEntity.status(400).build();
        List<LessonBlock> lessonBlocks = lessonBlockService.getAllByTeacherId(teacher.getId());
        List<LessonBlockDTO> lessonBlockDTOS = lessonBlocks.stream().map(LessonBlockDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(lessonBlockDTOS);
    }

    @GetMapping("/marks")
    public ResponseEntity<List<MarkDTO>> findMarksByLogin(Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());

        if (teacher == null) return ResponseEntity.status(400).build();
        List<Mark> marks = markRepository.findAllByTeacherId(teacher.getId());
        List<MarkDTO> markDTOS = marks.stream().map(MarkDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(markDTOS);
    }

    @GetMapping("/marks/{studentId}")
    public ResponseEntity<List<MarkDTO>> findMarksByLoginAndStudentId(@PathVariable("studentId") Long studentId,  Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());

        if (teacher == null) return ResponseEntity.status(400).build();
        List<Mark> marks = markRepository.findAllByTeacherIdAndStudentId(teacher.getId(), studentId);
        List<MarkDTO> markDTOS = marks.stream().map(MarkDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(markDTOS);
    }

    @GetMapping("/marks/{studentId}/{lessonId}")
    public ResponseEntity<List<MarkDTO>> findMarksByLoginAndStudentIdAndLessonId(@PathVariable("studentId") Long studentId, @PathVariable("lessonId") Long lessonId, Authentication auth) {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Teacher teacher = teacherService.getByLogin(userDetails.getUsername());

        if (teacher == null) return ResponseEntity.status(400).build();
        List<Mark> marks = markRepository.findAllByTeacherIdAndStudentIdAndLessonIdOrderByLessonId(teacher.getId(), studentId, lessonId);
        List<MarkDTO> markDTOS = marks.stream().map(MarkDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(markDTOS);
    }
}
