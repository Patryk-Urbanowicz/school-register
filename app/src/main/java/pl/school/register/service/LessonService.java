package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Lesson;
import pl.school.register.repositories.LessonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public Lesson addNew(Lesson lesson){
        return lessonRepository.save(lesson);
    }

    public List<Lesson> getAll(){
       return lessonRepository.findAll();
    }

    public Optional<Lesson> getById(Long id){
        return lessonRepository.findById(id);
    }

    public List<Lesson> getAllBySchoolClassId(Long id){
        return lessonRepository.findAllBySchoolClassId(id);
    }

    public List<Lesson> getAllByTeacherId(Long teacher_id){
        return lessonRepository.findAllByTeacherId(teacher_id);
    }

    public List<Lesson> getAllBySubjectId(Long id){
        return lessonRepository.findAllBySubjectId(id);
    }

}
