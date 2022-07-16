package pl.school.register.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Mark;
import pl.school.register.model.Student;
import pl.school.register.model.dto.LessonBlockDTO;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.model.enumerations.Role;
import pl.school.register.model.projections.AccountInfo;
import pl.school.register.service.AccountService;
import pl.school.register.service.LessonBlockService;
import pl.school.register.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {
    private StudentService studentService;
    private AccountService accountService;
    private LessonBlockService lessonBlockService;

    public StudentController(StudentService studentService, AccountService accountService,
                             LessonBlockService lessonBlockService) {
        this.studentService = studentService;
        this.accountService = accountService;
        this.lessonBlockService = lessonBlockService;
    }

    @GetMapping("/{login}")
    public ResponseEntity<AccountInfo> findByLogin(@PathVariable("login") String login){
        AccountInfo accountInfo = accountService.getInfoByLogin(login);
        if(accountInfo == null){
            return ResponseEntity.badRequest().build();
        }
        if(!accountInfo.getRole().equals(Role.STUDENT)){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(accountInfo);
    }

    @GetMapping("/{login}/marks")
    public ResponseEntity<List<MarkDTO>> findMarksByLogin(@PathVariable("login") String login){
        Student student = studentService.getByLogin(login);
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        List<MarkDTO> marks = student.getMarks().stream().map(MarkDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/{login}/schedule")
    public ResponseEntity<List<LessonBlockDTO>> findLessonBlocksByLogin(@PathVariable("login") String login){
        Student student = studentService.getByLogin(login);
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        List<LessonBlock> lessonBlocks = lessonBlockService.getAllBySchoolClassId(student.getSchoolClass().getId());
        List<LessonBlockDTO> result = lessonBlocks.stream().map(LessonBlockDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }


}
