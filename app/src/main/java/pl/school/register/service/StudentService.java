package pl.school.register.service;

import org.springframework.stereotype.Service;
import pl.school.register.model.Student;
import pl.school.register.repositories.StudentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addNew(Student student){
        studentRepository.save(student);
    }

    public List<Student> getAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> getById(Long id){
        return studentRepository.findById(id);
    }

    public Student getByLogin(String login){
        return studentRepository.findByLogin(login);
    }
}
