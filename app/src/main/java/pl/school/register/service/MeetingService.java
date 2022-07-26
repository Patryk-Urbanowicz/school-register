package pl.school.register.service;

import org.springframework.stereotype.Service;
import pl.school.register.model.Meeting;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.repositories.MeetingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;

    public MeetingService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Meeting addNew(Meeting meeting){
        return meetingRepository.save(meeting);
    }

    public List<Meeting> getAll(){
        return meetingRepository.findAll();
    }

    public Optional<Meeting> getById(Long id){
        return meetingRepository.findById(id);
    }

    public List<MeetingInWeek> getWithWeekDayByTeacherIdAndSchoolClassId(Long teacher_id,
                                                                         Long school_class_id,
                                                                         Long subject_id,
                                                                         LocalDate start,
                                                                         LocalDate end){
        return meetingRepository.findAllTeacherMeetingsInWeek(teacher_id, school_class_id, subject_id, start, end);
    }

    public List<MeetingInWeek> getWithWeekDayByTSchoolClassId(Long school_class_id,
                                                                         LocalDate start,
                                                                         LocalDate end){
        return meetingRepository.findAllSchoolClassMeetingsInWeek(school_class_id, start, end);
    }
}
