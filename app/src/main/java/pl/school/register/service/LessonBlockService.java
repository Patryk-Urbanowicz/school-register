package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.LessonBlock;
import pl.school.register.repositories.LessonBlockRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LessonBlockService {
    private final LessonBlockRepository lessonBlockRepository;

    public LessonBlockService(LessonBlockRepository lessonBlockRepository) {
        this.lessonBlockRepository = lessonBlockRepository;
    }

    public void addNew(LessonBlock lessonBlock){
        lessonBlockRepository.save(lessonBlock);
    }

    public List<LessonBlock> getAll(){
        return lessonBlockRepository.findAll();
    }

    public Optional<LessonBlock> getById(Long id){
        return lessonBlockRepository.findById(id);
    }

    public List<LessonBlock> getAllByLessonId(Long id){
        return lessonBlockRepository.findAllByLessonId(id);
    }

    public List<LessonBlock> getAllByTeacherId(Long teacher_id){
        return lessonBlockRepository.findAllByTeacherId(teacher_id);
    }

    public List<LessonBlock> getAllBySchoolClassId(Long school_class_id){
        return lessonBlockRepository.findAllBySchoolClassId(school_class_id);
    }
}