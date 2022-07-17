package pl.school.register.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.school.register.model.Meeting;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class MeetingDTO {
    private Long id;
    private Long lessonId;
    private Long teacherId;
    private LocalDateTime time;
    String topic;
    String room;

    public MeetingDTO(Meeting meeting) {
        this.id = meeting.getId();
        this.lessonId = meeting.getLesson().getId();
        this.teacherId = meeting.getTeacher().getId();
        this.time = meeting.getTime();
        this.topic = meeting.getTopic();
        this.room = meeting.getRoom();
    }
}
