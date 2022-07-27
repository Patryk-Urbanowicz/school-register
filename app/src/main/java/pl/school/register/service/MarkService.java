package pl.school.register.service;

import org.springframework.stereotype.Service;
import pl.school.register.model.Mark;
import pl.school.register.model.SubjectAvgPair;
import pl.school.register.model.dto.MarkDTO;
import pl.school.register.model.dto.mapper.MappingUtils;
import pl.school.register.repositories.MarkRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MarkService {
    private final MarkRepository markRepository;
    private final MappingUtils mappingUtils;

    public MarkService(MarkRepository markRepository, MappingUtils mappingUtils) {
        this.markRepository = markRepository;
        this.mappingUtils = mappingUtils;
    }

    public Mark addNew(Mark mark) throws Exception {
        if (mark.getId() == null){
            List<Mark> m = markRepository.findByStudentIdAndLabel(mark.getStudent().getId(), mark.getLabel());
            if (!m.isEmpty()) throw new Exception("cos nie działa");
        }
        return markRepository.save(mark);
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

    public List<Mark> getAllByStudentIdAndLessonId(Long student_id, Long lesson_id){
        return markRepository.findAllByStudentIdAndLessonIdOrderByLabel(student_id, lesson_id);
    }

    public List<Mark> getAllByTeacherIdAndLessonId(Long teacher_id, Long lesson_id){
        return markRepository.findAllByTeacherIdAndLessonIdOrderByLabel(teacher_id, lesson_id);
    }

    public Mark getByLabel(String label){
        return markRepository.findByLabel(label);
    }

    public void deleteById(Long id){
        markRepository.deleteById(id);
    }

    public void update(Mark mark){
        markRepository.save(mark);
    }

    public Float getWeightedAverageByStudentIdAndLessonId(Long student_id, Long lesson_id) throws Exception {
        Optional<Float> chck = markRepository.findWeightedAverageByStudentIdAndLessonId(student_id, lesson_id);
        if(chck.isEmpty()) throw new Exception("Couldn't estimate the average");
        return chck.get();
    }

    public List<SubjectAvgPair> getAllWeightedAverageForStudentGroupingBySubject(Long school_class_id, Long student_id) {
        return markRepository.findAllWeightedAverageForStudentGroupingBySubject(school_class_id, student_id);
    }

    public Mark mapDTOToModel(MarkDTO markDTO){
        return mappingUtils.mapFromDTO(markDTO, Mark.class);
    }
}
