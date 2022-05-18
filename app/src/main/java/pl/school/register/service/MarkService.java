package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Mark;
import pl.school.register.repositories.MarkRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MarkService {

    private final MarkRepository markRepository;

    @Autowired
    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public void addMark(@RequestBody Mark mark){
        markRepository.save(mark);
    }

    public List<Mark> getMarks(){
        return markRepository.findAll();
    }

    public Optional<Mark> getMarkById(Long id){
        return markRepository.findById(id);
    }

    public List<Mark> getMarksForStudentId(Long id){
        return markRepository.findMarksForStudentId(id);
    }

    public List<Mark> getMarksForStudentIdAndSubjectId(Long student_id, Long subject_id){
        return markRepository.findMarksForStudentIdAndSubjectId(student_id, subject_id);
    }

}
