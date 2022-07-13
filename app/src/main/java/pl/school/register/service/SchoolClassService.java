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

    public final SchoolClassRepository schoolClassRepository;

    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public void addSchoolClass(SchoolClass schoolClass){
        schoolClassRepository.save(schoolClass);
    }

    public List<SchoolClass> getSchoolClasses(){
        return schoolClassRepository.findAll();
    }

    public Optional<SchoolClass> getSchoolClassById(Long id){
        return schoolClassRepository.findById(id);
    }

    public Optional<SchoolClass> getSchoolClassByHomeroomTeacherId(Long id){
        return schoolClassRepository.findSchoolClassByHomeroomTeacherId(id);
    }

    public List<SchoolClass> getAllByTeacherWhoHasLessonsWith(Long teacher_id){
        return schoolClassRepository.findAllByForTeacherWhoHasLessonsWith(teacher_id);
    }
}
