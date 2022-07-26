package pl.school.register.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.school.register.model.*;
import pl.school.register.model.enumerations.AttendanceStatus;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.repositories.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class BootStrapData implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final SubjectRepository subjectRepository;
    private final LessonRepository lessonRepository;
    private final AttendanceRepository attendanceRepository;
    private final MeetingRepository meetingRepository;
    private final LessonBlockRepository lessonBlockRepository;
    private final MarkRepository markRepository;
    private final PasswordEncoder passwordEncoder;

    public BootStrapData(StudentRepository studentRepository, ParentRepository parentRepository, TeacherRepository teacherRepository, SchoolClassRepository schoolClassRepository,
                         SubjectRepository subjectRepository,
                         LessonRepository lessonRepository, AttendanceRepository attendanceRepository,
                         LessonBlockRepository lessonBlockRepository, MeetingRepository meetingRepository, MarkRepository markRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.parentRepository = parentRepository;
        this.teacherRepository = teacherRepository;
        this.schoolClassRepository = schoolClassRepository;
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

        Teacher teacher1 = new Teacher();
        teacher1.setFirstName("Jan");
        teacher1.setLastName("Burczymucha");
        teacher1.setLogin("JanuszMucha");
        teacher1.setPassword(passwordEncoder.encode("BurczyBrzucha"));

        Teacher teacher2 = new Teacher();
        teacher2.setFirstName("Anna");
        teacher2.setLastName("Kowalska");
        teacher2.setLogin("miniAnia");
        teacher2.setPassword(passwordEncoder.encode("wyzimskiJednorozec"));

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName("Kleks class");
        schoolClass.setProfile("Magic-Creativity");
        schoolClass.setHomeroomTeacher(teacher);

        SchoolClass schoolClass1 = new SchoolClass();
        schoolClass1.setClassName("Burczymuszki");
        schoolClass1.setProfile("Latanie i burczenie");
        schoolClass1.setHomeroomTeacher(teacher2);

        Student student = new Student();
        student.setFirstName("Adas");
        student.setLastName("Niezgodka");
        student.setLogin("adas69PL");
        student.setPassword(passwordEncoder.encode("DzikiDzikus"));
        student.setSchoolClass(schoolClass);
        student.setIndex(1L);

        Student student1 = new Student();
        student1.setFirstName("Karolina");
        student1.setLastName("Koralowa");
        student1.setLogin("krolowaKorali");
        student1.setPassword(passwordEncoder.encode("CzerwoneJakWino"));
        student1.setSchoolClass(schoolClass);
        student1.setIndex(2L);

        Student student2 = new Student();
        student2.setFirstName("Michal");
        student2.setLastName("Bak");
        student2.setLogin("bananowySamuraj");
        student2.setPassword(passwordEncoder.encode("Ratatouille"));
        student2.setSchoolClass(schoolClass);
        student2.setIndex(3L);

        Student student3 = new Student();
        student3.setFirstName("Jerzy");
        student3.setLastName("Andrzejewski");
        student3.setLogin("ShimazuAmarasu");
        student3.setPassword(passwordEncoder.encode("MangekyoSharingan"));
        student3.setSchoolClass(schoolClass);
        student3.setIndex(4L);

        Student student4 = new Student();
        student4.setFirstName("Michal");
        student4.setLastName("Szpak");
        student4.setLogin("Wielkooki");
        student4.setPassword(passwordEncoder.encode("WielkieOczy"));
        student4.setSchoolClass(schoolClass);
        student4.setIndex(5L);

        //students from another class
        Student student5 = new Student();
        student5.setFirstName("Lola");
        student5.setLastName("Bola");
        student5.setLogin("wienerBerlin");
        student5.setPassword(passwordEncoder.encode("kebsikRollo"));
        student5.setSchoolClass(schoolClass1);
        student5.setIndex(6L);

        Student student6 = new Student();
        student6.setFirstName("Bugs");
        student6.setLastName("Bunny");
        student6.setLogin("DoktorekPerkusista");
        student6.setPassword(passwordEncoder.encode("NoCoTamDoktorku"));
        student6.setSchoolClass(schoolClass1);
        student6.setIndex(7L);

        Parent parent = new Parent();
        parent.setFirstName("Leosia");
        parent.setLastName("Yung");
        parent.setLogin("YungStar");
        parent.setPassword(passwordEncoder.encode("ytDestroyer"));
        parent.getChildren().add(student);
        parent.getChildren().add(student3);
        parent.getChildren().add(student4);
        parent.getChildren().add(student5);

        Parent parent1 = new Parent();
        parent1.setFirstName("Leopold");
        parent1.setLastName("Staff");
        parent1.setLogin("LeoRzeczy");
        parent1.setPassword(passwordEncoder.encode("WielkiPisarz"));
        parent1.getChildren().add(student);
        parent1.getChildren().add(student1);
        parent1.getChildren().add(student2);
        parent1.getChildren().add(student6);

        Subject subject = new Subject();
        subject.setSubjectName("Math");
        teacher.getSubjects().add(subject);

        Subject subject1 = new Subject();
        subject1.setSubjectName("Polish Language");
        teacher.getSubjects().add(subject1);

        Subject subject2 = new Subject();
        subject2.setSubjectName("Driving class");
        teacher1.getSubjects().add(subject2);

        Lesson lesson = new Lesson();
        lesson.setSchoolClass(schoolClass);
        lesson.setSubject(subject);
        lesson.setTeacher(teacher);

        Lesson lesson1 = new Lesson();
        lesson1.setSchoolClass(schoolClass);
        lesson1.setSubject(subject1);
        lesson1.setTeacher(teacher);

        Lesson lesson2 = new Lesson();
        lesson2.setSchoolClass(schoolClass1);
        lesson2.setSubject(subject2);
        lesson2.setTeacher(teacher2);

        Meeting meeting = new Meeting();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        meeting.setTime(LocalDateTime.parse("2022-07-29 09:00", dtf));
        meeting.setTopic("Lekcja 1: Krzywa Beziera");
        meeting.setLesson(lesson);
        meeting.setTeacher(teacher);
        meeting.setRoom("L3");

        Meeting meeting1 = new Meeting();
        meeting1.setTime(LocalDateTime.now());
        meeting1.setTopic("Lekcja2: krzywa B-spline");
        meeting1.setLesson(lesson);
        meeting1.setTeacher(teacher);
        meeting1.setRoom("L3");

        Meeting meeting2 = new Meeting();
        meeting2.setTime(LocalDateTime.now());
        meeting2.setTopic("Lekcja 1: Dlaczego Mickiewicz wielkim poetą był");
        meeting2.setLesson(lesson1);
        meeting2.setTeacher(teacher);
        meeting2.setRoom("S2");

        LessonBlock lessonBlock = new LessonBlock();
        lessonBlock.setLesson(lesson);
        lessonBlock.setDuration(45);
        lessonBlock.setStartTime("9:00");
        lessonBlock.setWeekDay(WeekDay.FRIDAY);

        LessonBlock lessonBlock1 = new LessonBlock();
        lessonBlock1.setLesson(lesson1);
        lessonBlock1.setDuration(45);
        lessonBlock1.setStartTime("11:00");
        lessonBlock1.setWeekDay(WeekDay.THURSDAY);

        LessonBlock lessonBlock2 = new LessonBlock();
        lessonBlock2.setLesson(lesson1);
        lessonBlock2.setDuration(45);
        lessonBlock2.setStartTime("13:00");
        lessonBlock2.setWeekDay(WeekDay.WEDNESDAY);

        LessonBlock lessonBlock3 = new LessonBlock();
        lessonBlock3.setLesson(lesson1);
        lessonBlock3.setDuration(90);
        lessonBlock3.setStartTime("15:00");
        lessonBlock3.setWeekDay(WeekDay.TUESDAY);

        LessonBlock lessonBlock4 = new LessonBlock();
        lessonBlock4.setLesson(lesson1);
        lessonBlock4.setDuration(90);
        lessonBlock4.setStartTime("13:00");
        lessonBlock4.setWeekDay(WeekDay.FRIDAY);

        LessonBlock lessonBlock5 = new LessonBlock();
        lessonBlock5.setLesson(lesson);
        lessonBlock5.setDuration(90);
        lessonBlock5.setStartTime("15:00");
        lessonBlock5.setWeekDay(WeekDay.FRIDAY);

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

        Mark mark2 = new Mark();
        mark2.setStudent(student);
        mark2.setTeacher(teacher);
        mark2.setLesson(lesson);
        mark2.setTimestamp(Timestamp.valueOf("2022-06-20 16:44:00"));
        mark2.setLabel("Zadanie2");
        mark2.setDescription("ocena za zadanie z krzywej beziera");
        mark2.setValue(3);
        mark2.setWeight(3);

        subjectRepository.save(subject);
        subjectRepository.save(subject1);
        subjectRepository.save(subject2);
        teacherRepository.save(teacher);
        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);
        schoolClassRepository.save(schoolClass);
        schoolClassRepository.save(schoolClass1);
        lessonRepository.save(lesson);
        lessonRepository.save(lesson1);
        lessonRepository.save(lesson2);
        studentRepository.save(student);
        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
        studentRepository.save(student4);
        studentRepository.save(student5);
        studentRepository.save(student6);
        parentRepository.save(parent);
        parentRepository.save(parent1);
        meetingRepository.save(meeting);
        meetingRepository.save(meeting1);
        meetingRepository.save(meeting2);
        lessonBlockRepository.save(lessonBlock);
        lessonBlockRepository.save(lessonBlock1);
        lessonBlockRepository.save(lessonBlock2);
        lessonBlockRepository.save(lessonBlock3);
        lessonBlockRepository.save(lessonBlock4);
        lessonBlockRepository.save(lessonBlock5);
        markRepository.save(mark);
        markRepository.save(mark2);
        attendanceRepository.save(attendance);
    }
}
