package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Subject;
import pl.school.register.model.Teacher;
import pl.school.register.repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addNew(Teacher teacher){
        teacherRepository.save(teacher);
    }

    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getById(Long id){
        return teacherRepository.findById(id);
    }
}
