package pl.school.register.view.components;

import pl.school.register.model.Mark;
import pl.school.register.model.Subject;

import java.util.List;

public class MarkRow extends Row{
    public MarkRow(Subject subject, List<Mark> marks){
        add(new RowSegment(subject.getSubjectName()));
        add(new MarkTableSegment(marks));
    }
}
