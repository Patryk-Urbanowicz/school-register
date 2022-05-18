package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Meeting;
import pl.school.register.model.Parent;
import pl.school.register.repositories.ParentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ParentService {

    public final ParentRepository parentRepository;

    @Autowired
    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public void addParent(@RequestBody Parent parent){
        parentRepository.save(parent);
    }

    public List<Parent> getParents(){
        return parentRepository.findAll();
    }

    public Optional<Parent> getParentById(Long id){
        return parentRepository.findById(id);
    }

    public Optional<Parent> getParentByStudentId(Long id){
        return parentRepository.findByStudentId(id);
    }
}
