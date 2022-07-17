package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Student;
import pl.school.register.model.Subject;
import pl.school.register.repositories.SubjectRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public Subject addNew(Subject subject){
        return subjectRepository.save(subject);
    }

    public List<Subject> getAll(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> getById(Long id){
        return subjectRepository.findById(id);
    }
}
