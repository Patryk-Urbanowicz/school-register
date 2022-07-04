package pl.school.register.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import pl.school.register.model.*;
import pl.school.register.model.enumerations.WeekDay;
import pl.school.register.view.components.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(value = "account/schedule", layout = AccountMiddlePanelNestedLayout.class)
public class StudentScheduleView extends VerticalLayout {
    Student student1 = new Student();
    List<LessonBlock> blocks = new ArrayList<>();
    public StudentScheduleView(){
		sampleData();
        setSizeFull();
        ScheduleTable st = new ScheduleTable(blocks);
        ResponsiveTableWrapper wrapper = new ResponsiveTableWrapper(st);
		add(wrapper);
    }

    private void sampleData(){
        List<Mark> marks = new ArrayList<>();
        Teacher teacher = new Teacher();
        teacher.setFirstName("John");
        teacher.setLastName("Doe");
        teacher.setLogin("john+doe");

        Subject pszyra = new Subject();
        pszyra.setSubjectName("Pszyra");
        teacher.setSubjects(Set.of(pszyra));

        Lesson lesson = new Lesson();
        lesson.setSubject(pszyra);
        lesson.setTeacher(teacher);

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
        lessonBlock.setLesson(lesson);

        LessonBlock lessonBlock2 = new LessonBlock();
        lessonBlock2.setDuration(45);
        lessonBlock2.setStartTime("9:00");
        lessonBlock2.setWeekDay(WeekDay.MONDAY);
        lessonBlock2.setLesson(lesson);

        LessonBlock lessonBlock3 = new LessonBlock();
        lessonBlock3.setDuration(45);
        lessonBlock3.setStartTime("10:00");
        lessonBlock3.setWeekDay(WeekDay.MONDAY);
        lessonBlock3.setLesson(lesson);

        LessonBlock lessonBlock4 = new LessonBlock();
        lessonBlock4.setDuration(45);
        lessonBlock4.setStartTime("11:00");
        lessonBlock4.setWeekDay(WeekDay.MONDAY);
        lessonBlock4.setLesson(lesson);

        LessonBlock lessonBlock5 = new LessonBlock();
        lessonBlock5.setDuration(45);
        lessonBlock5.setStartTime("10:00");
        lessonBlock5.setWeekDay(WeekDay.TUESDAY);
        lessonBlock5.setLesson(lesson);

        LessonBlock lessonBlock6 = new LessonBlock();
        lessonBlock6.setDuration(45);
        lessonBlock6.setStartTime("11:00");
        lessonBlock6.setWeekDay(WeekDay.TUESDAY);
        lessonBlock6.setLesson(lesson);

        LessonBlock lessonBlock7 = new LessonBlock();
        lessonBlock7.setDuration(45);
        lessonBlock7.setStartTime("8:00");
        lessonBlock7.setWeekDay(WeekDay.WEDNESDAY);
        lessonBlock7.setLesson(lesson);

        blocks.addAll(List.of(lessonBlock, lessonBlock2, lessonBlock3, lessonBlock4, lessonBlock5, lessonBlock6, lessonBlock7));



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
        p.setValue(5);
        p.setStudent(student1);
        p.setLesson(lesson);
        p.setWeight(2);
        marks.add(bdb);
        student1.setMarks(new HashSet<>(marks));
    }
}
