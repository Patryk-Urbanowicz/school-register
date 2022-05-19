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

    public void addSubject(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> getSubjects(){
        return subjectRepository.findAll();
    }

    public Optional<Subject> getSubjectById(Long id){
        return subjectRepository.findById(id);
    }
}
