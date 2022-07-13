package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Mark;
import pl.school.register.model.Student;
import pl.school.register.model.enumerations.WeekDay;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Set;

@Tag("tr")
public class StudentRow extends Div {
    public StudentRow(Student student, List<String> columns){
        Set<Mark> marks = student.getMarks();
        Deque<Mark> marksDeque = new ArrayDeque(marks);
        String fullName = String.format("%s %s", student.getFirstName(), student.getLastName());
        add(new StudentListSegment(fullName));
        for (int i=0; i < columns.size(); i++){
            if (!marksDeque.isEmpty()){
                Mark mark = marksDeque.peek();
                String markLabel = columns.get(i);
                if (markLabel.equals(mark.getLabel())){
                    add(new StudentListMarkSegment(mark));
                    marksDeque.pop();
                    continue;
                }
            }
            add(new RowSegment());
        }
    }
}
