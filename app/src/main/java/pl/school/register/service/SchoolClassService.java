package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Parent;
import pl.school.register.model.SchoolClass;
import pl.school.register.repositories.SchoolClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolClassService {
    private final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public SchoolClass addNew(SchoolClass schoolClass){
        return schoolClassRepository.save(schoolClass);
    }

    public List<SchoolClass> getAll(){
        return schoolClassRepository.findAll();
    }

    public Optional<SchoolClass> getById(Long id){
        return schoolClassRepository.findById(id);
    }

    public Optional<SchoolClass>getByHomeroomTeacherId(Long id){
        return schoolClassRepository.findByHomeroomTeacherId(id);
    }

    public List<SchoolClass> getAllByTeacherWhoHasLessonsWith(Long teacher_id){
        return schoolClassRepository.findAllByForTeacherWhoHasLessonsWith(teacher_id);
    }
}
