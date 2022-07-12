package pl.school.register.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.school.register.model.*;
import pl.school.register.model.enumerations.AttendanceStatus;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final Student_ClassRepository student_classRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;
    private final MeetingRepository meetingRepository;
    private final LessonBlockRepository lessonBlockRepository;
    private final MarkRepository markRepository;
    private final PasswordEncoder passwordEncoder;

    public BootStrapData(StudentRepository studentRepository, ParentRepository parentRepository, TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository,
                         Student_ClassRepository student_classRepository, SubjectRepository subjectRepository,
                         LessonRepository lessonRepository, AttendanceRepository attendanceRepository,
                         LessonBlockRepository lessonBlockRepository, MeetingRepository meetingRepository, MarkRepository markRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
        this.student_classRepository = student_classRepository;
        this.subjectRepository = subjectRepository;
        this.lessonRepository = lessonRepository;
        this.attendanceRepository = attendanceRepository;
        this.meetingRepository = meetingRepository;
        this.lessonBlockRepository = lessonBlockRepository;
        this.markRepository = markRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        Teacher teacher = new Teacher();
        teacher.setFirstName("Pan");
        teacher.setLastName("Kleks");
        teacher.setLogin("mrKleks");
        teacher.setPassword(passwordEncoder.encode("childLabor"));

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName("Kleks class");
        schoolClass.setProfile("Magic-Creativity");
        schoolClass.setHomeroomTeacher(teacher);

        Student student = new Student();
        student.setFirstName("Adas");
        student.setLastName("Niezgodka");
        student.setLogin("adas69PL");
        student.setPassword(passwordEncoder.encode("DzikiDzikus"));
        student.setSchoolClass(schoolClass);

        Parent parent = new Parent();
        parent.setFirstName("Leosia");
        parent.setLastName("Yung");
        parent.setLogin("YungStar");
        parent.setPassword(passwordEncoder.encode("ytDestroyer"));
        parent.getChildren().add(student);

        Student_Class studentClass = new Student_Class();
        studentClass.setStudent(student);
        studentClass.setSchoolClass(schoolClass);
        studentClass.setIndex(1L);


        Subject subject = new Subject();
        subject.setSubjectName("Math");
        teacher.getSubjects().add(subject);

        Lesson lesson = new Lesson();
        lesson.setSchoolClass(schoolClass);
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);

        Meeting meeting = new Meeting();
        meeting.setTime(LocalDateTime.now());
        meeting.setTopic("Lekcja 1: Krzywa Beziera");
        meeting.setLesson(lesson);
        meeting.setTeacher(teacher);
        meeting.setRoom("L3");

        LessonBlock lessonBlock = new LessonBlock();
        lessonBlock.setLesson(lesson);
        lessonBlock.setDuration(45);
        lessonBlock.setStartTime("9:00");
        lessonBlock.setWeekDay(WeekDay.MONDAY);

        Attendance attendance = new Attendance();
        attendance.setStudent(student);
        attendance.setAttendanceStatus(AttendanceStatus.ATTENDED);
        attendance.setMeeting(meeting);

        Mark mark = new Mark();
        mark.setStudent(student);
        mark.setTeacher(teacher);
        mark.setLesson(lesson);
        mark.setTimestamp(Timestamp.valueOf("2022-06-20 16:44:00"));
        mark.setLabel("Zadanie");
        mark.setDescription("ocena za zadanie z krzywej beziera");
        mark.setValue(2);
        mark.setWeight(5);

        subjectRepository.save(subject);
        teacherRepository.save(teacher);
        schoolClassRepository.save(schoolClass);
        lessonRepository.save(lesson);
        studentRepository.save(student);
        parentRepository.save(parent);
        student_classRepository.save(studentClass);
        lessonBlockRepository.save(lessonBlock);
        meetingRepository.save(meeting);
        markRepository.save(mark);
        attendanceRepository.save(attendance);
    }
}
