package pl.school.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.school.register.model.Meeting;
import pl.school.register.repositories.MeetingRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public void addNew(Meeting meeting){
        meetingRepository.save(meeting);
    }

    public List<Meeting> getAll(){
        return meetingRepository.findAll();
    }

    public Optional<Meeting> getById(Long id){
        return meetingRepository.findById(id);
    }
}
