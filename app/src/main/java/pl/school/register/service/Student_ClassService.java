package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.SchoolClass;
import pl.school.register.model.Student_Class;
import pl.school.register.repositories.Student_ClassRepository;

import java.util.List;
import java.util.Optional;

@Service
public class Student_ClassService {

    public final Student_ClassRepository student_classRepository;

    public Student_ClassService(Student_ClassRepository student_classRepository) {
        this.student_classRepository = student_classRepository;
    }

    public void addStudent_Class(Student_Class student_class){
        student_classRepository.save(student_class);
    }

    public List<Student_Class> getStudent_Classes(){
        return student_classRepository.findAll();
    }

    public Optional<Student_Class> getStudent_ClassById(Long id){
        return student_classRepository.findById(id);
    }

    public List<Student_Class> getAllBySchoolClassId(Long school_class_id){
        return student_classRepository.findAllBySchoolClassIdOrderByIndex(school_class_id);
    }

}
