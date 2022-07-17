package pl.school.register.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.school.register.model.Lesson;
import pl.school.register.model.Mark;
import pl.school.register.model.Student;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.service.MarkService;
import pl.school.register.service.StudentService;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/mark")
public class MarkController {
    private MarkService markService;
    private StudentService studentService;

    public MarkController(MarkService markService, StudentService studentService) {
        this.markService = markService;
        this.studentService = studentService;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addNewMark(@RequestBody MarkDTO markDTO){
        if(markDTO.getId() != null) return ResponseEntity.badRequest().build();
        Mark mark = markService.mapDTOToModel(markDTO);
        if(studentService.getById(markDTO.getStudentId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Mark newMark = markService.addNew(mark);
        return ResponseEntity.ok(newMark.getId());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> updateMark(@RequestBody MarkDTO markDTO){
        if(markDTO.getId() == null) return ResponseEntity.badRequest().build();
        if(studentService.getById(markDTO.getStudentId()).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Mark> test = markService.getById(markDTO.getId());
        if(test.isEmpty()) return ResponseEntity.badRequest().build();
        Mark mark2 = test.get();
        if(markDTO.getLabel() != null) mark2.setLabel(markDTO.getLabel());
        if(markDTO.getDescription() != null) mark2.setDescription(markDTO.getDescription());
        if(markDTO.getValue() != null) mark2.setValue(markDTO.getValue());
        if(markDTO.getWeight() != null) mark2.setWeight(markDTO.getWeight());
        markService.addNew(mark2);
        return ResponseEntity.ok(mark2.getId());
    }

    @GetMapping("/student/{login}")
    public ResponseEntity<Map<String, List<MarkDTO>>> findMarksByLogin(@PathVariable("login") String login){
        Student student = studentService.getByLogin(login);
        if(student == null){
            return ResponseEntity.badRequest().build();
        }
        List<Lesson> l1 = new ArrayList<>(student.getSchoolClass().getLessons());
        Map<String, List<MarkDTO>> m1 = new HashMap<>();
        for(Lesson l : l1){
            List<MarkDTO> o1 = markService.getAllByStudentIdAndLessonId(student.getId(),
                    l.getId()).stream().map(MarkDTO::new).collect(Collectors.toList());
            m1.put(l.getSubject().getSubjectName(),o1);
        }
        return ResponseEntity.ok(m1);
    }


}
