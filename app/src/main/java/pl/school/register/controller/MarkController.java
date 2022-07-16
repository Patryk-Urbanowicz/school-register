package pl.school.register.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.school.register.model.Mark;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.service.MarkService;

@RestController
@RequestMapping(value = "/api/mark")
public class MarkController {
    private MarkService markService;

    public MarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping(value = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> addNewMark(@RequestBody MarkDTO markDTO){
        Mark mark = markService.mapDTOToModel(markDTO);
        markService.addNew(mark);
        return ResponseEntity.ok(1L);
    }
}
