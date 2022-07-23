package pl.school.register.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.register.model.Parent;
import pl.school.register.model.dto.StudentInfoDTO;
import pl.school.register.service.ParentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/parent")
public class ParentController {
    private ParentService parentService;

    public ParentController(ParentService parentService) {
        this.parentService = parentService;
    }

    @GetMapping(value = "/children", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentInfoDTO>> getParentChildren(Authentication auth){
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Parent p1 = parentService.getByLogin(userDetails.getUsername());
        if(p1 == null) return ResponseEntity.badRequest().build();
        List<StudentInfoDTO> l1 = p1.getChildren().stream().map(StudentInfoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(l1);
    }
}
