package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Mark;
import pl.school.register.model.SubjectAvgPair;
import pl.school.register.repositories.MarkRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MarkService {
    private final MarkRepository markRepository;

    public MarkService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public void addNew(Mark mark){
        markRepository.save(mark);
    }

    public List<Mark> getAll(){
        return markRepository.findAll();
    }

    public Optional<Mark> getById(Long id){
        return markRepository.findById(id);
    }

    public List<Mark> getAllByStudentId(Long id){
        return markRepository.findAllByStudentId(id);
    }

    public List<Mark> getAllByStudentIdAndSubjectId(Long student_id, Long lesson_id){
        return markRepository.findAllByStudentIdAndLessonId(student_id, lesson_id);
    }

    public Float getWeightedAverageByStudentIdAndLessonId(Long student_id, Long lesson_id) throws Exception {
        Optional<Float> chck = markRepository.findWeightedAverageByStudentIdAndLessonId(student_id, lesson_id);
        if(chck.isEmpty()) throw new Exception("Couldn't estimate the average");
        return chck.get();
    }

    public List<SubjectAvgPair> getAllWeightedAverageForStudentGroupingBySubject(Long school_class_id, Long student_id) {
        return markRepository.findAllWeightedAverageForStudentGroupingBySubject(school_class_id, student_id);
    }
}
