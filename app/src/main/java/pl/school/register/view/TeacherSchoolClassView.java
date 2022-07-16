package pl.school.register.view;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import pl.school.register.model.*;
import pl.school.register.model.projections.MeetingInWeek;
import pl.school.register.service.MeetingService;
import pl.school.register.view.components.ResponsiveTableWrapper;
import pl.school.register.view.components.ScheduleTable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Route(value = "teacher/class/:classId/subject/:subjectId", layout = TeacherLayout.class)
public class TeacherSchoolClassView extends VerticalLayout implements BeforeEnterObserver {
    private SchoolClass schoolClass;
    private MeetingService meetingService;
    public TeacherSchoolClassView(MeetingService meetingService){
        this.meetingService = meetingService;
        setClassName("school-class-view");
        removeAll();
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        removeAll(); //A very bad way of "refreshing" components on page

        RouteParameters params = beforeEnterEvent.getRouteParameters();
        Optional<String> subjectId = params.get("subjectId");
        Optional<String> classId = params.get("classId");
        if (subjectId.isPresent() && classId.isPresent()){
            LocalDate now = LocalDate.now();
            TemporalField fieldISO = WeekFields.of(Locale.GERMANY).dayOfWeek();
            System.out.println(now.with(fieldISO, 1));
            System.out.println(now.with(fieldISO, 5));

            LocalDate monday = now.with(fieldISO, 1);
            LocalDate friday = now.with(fieldISO, 5);

            Long subjectIdL = Long.parseLong(subjectId.get());
            Long classIdL = Long.parseLong(classId.get());
            List<MeetingInWeek> meetingsInWeek = meetingService
                    .getWithWeekDayByTeacherIdAndSchoolClassId(1L, classIdL, subjectIdL,
                                                                monday, friday);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            List<LessonBlock> blocks = meetingsInWeek.stream().map(m -> {
                LessonBlock lb = new LessonBlock();
                Subject subject = new Subject();
                subject.setSubjectName(m.getSubjectName());
                Lesson l = new Lesson();
                Teacher teacher = new Teacher();
                teacher.setFirstName(m.getTeacherFirstName());
                teacher.setLastName(m.getTeacherLastName());
                l.setTeacher(teacher);
                lb.setWeekDay(m.getWeekDay());
                LocalDateTime time = m.getLessonStartTime();
                lb.setStartTime(time.format(dtf));
                lb.setLesson(l);
                return lb;
            }).collect(Collectors.toList());

            add(new H1("Class: " + classIdL +  " Subject: " + subjectIdL ));
            add(new ResponsiveTableWrapper(new ScheduleTable(blocks)));
        }

    }
}
