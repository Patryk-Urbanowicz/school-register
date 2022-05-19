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

    public void addLessonBlock(LessonBlock lessonBlock){
        lessonBlockRepository.save(lessonBlock);
    }

    public List<LessonBlock> getLessonBlocks(){
        return lessonBlockRepository.findAll();
    }

    public Optional<LessonBlock> getLessonBlockById(Long id){
        return lessonBlockRepository.findById(id);
    }

    public List<LessonBlock> getLessonBlocksByLessonId(Long id){
        return lessonBlockRepository.findLessonBlocksByLessonId(id);
    }



}
