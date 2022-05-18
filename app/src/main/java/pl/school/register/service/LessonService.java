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

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public void addLessons(@RequestBody Lesson lesson){
        lessonRepository.save(lesson);
    }

    public List<Lesson> getLessons(){
       return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Long id){
        return lessonRepository.findById(id);
    }

    public List<Lesson> getLessonBySchoolClassId(Long id){
        return lessonRepository.findLessonBySchoolClassId(id);
    }

    public List<Lesson> getLessonByTeacherId(Long id){
        return lessonRepository.findLessonByTeacherId(id);
    }

    public List<Lesson> getLessonBySubjectId(Long id){
        return lessonRepository.findLessonBySubjectId(id);
    }


}
