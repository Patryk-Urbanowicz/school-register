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
    private final ParentRepository parentRepository;

    public ParentService(ParentRepository parentRepository) {
        this.parentRepository = parentRepository;
    }

    public Parent addNew(Parent parent){
        return parentRepository.save(parent);
    }

    public List<Parent> getAll(){
        return parentRepository.findAll();
    }

    public Optional<Parent> getById(Long id){
        return parentRepository.findById(id);
    }

    public Parent getByLogin(String login){
        return parentRepository.findByLogin(login);
    }
}
