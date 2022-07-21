package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.school.register.model.*;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.view.components.MarkTable;
import pl.school.register.view.components.ResponsiveTableWrapper;

import javax.annotation.security.RolesAllowed;
import java.util.*;
import java.util.stream.Collectors;

@RolesAllowed(value = {"STUDENT"})
@Route(value = "account/marks", layout = AccountMiddlePanelNestedLayout.class)
public class StudentMarksView extends VerticalLayout {
    Student student1 = new Student();
    private List<Mark> marks;
    public StudentMarksView(){
        sampleData();
        Map<Subject, List<Mark>> map = marks.stream().collect(Collectors.groupingBy(mark
                -> mark.getLesson().getSubject()));
        MarkTable mt = new MarkTable(map);
        ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(mt);
        add(wrapper);
    }


    private void sampleData(){
        marks = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setLogin("john+doe");

        Subject pszyra = new Subject();
        pszyra.setSubjectName("Pszyra");
        teacher.setSubjects(Set.of(pszyra));

        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setClassName("A");
        schoolClass.setProfile("pszyrodniczy");
        schoolClass.setHomeroomTeacher(teacher);


        student1.setSchoolClass(schoolClass);
        student1.setId(123L);
        student1.setFirstName("aaa");
        student1.setLastName("bbb");

        Student student2 = new Student();
        student2.setSchoolClass(schoolClass);
        student2.setId(1L);
        student2.setFirstName("ccc");
        student2.setLastName("ddd");

        LessonBlock lessonBlock = new LessonBlock();
        lessonBlock.setDuration(45);
        lessonBlock.setStartTime("8:00");
        lessonBlock.setWeekDay(WeekDay.MONDAY);

        Lesson lesson = new Lesson();
        lesson.setSubject(pszyra);
        lesson.setTeacher(teacher);
        lesson.setLessonBlocks(Set.of(lessonBlock));

        Meeting meeting = new Meeting();
        meeting.setTopic("pain");
        meeting.setLesson(lesson);

        Mark p = new Mark();
        p.setValue(1);
        p.setStudent(student1);
        p.setLesson(lesson);
        p.setWeight(4);
        marks.add(p);

        Mark bdb = new Mark();
        bdb.setValue(5);
        bdb.setStudent(student1);
        bdb.setLesson(lesson);
        bdb.setWeight(2);
        marks.add(bdb);
        student1.setMarks(new HashSet<>(marks));

    }
}
