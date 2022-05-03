package pl.school.register.view.components;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.school.register.model.Lesson;
import pl.school.register.model.LessonBlock;
import pl.school.register.model.Teacher;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class Column extends VerticalLayout {
    //TODO:
    //There should be an array of lessons
    //We should iterate over it and add ColumnSegments in a loop
    public Column(String weekday, Deque<LessonBlock> lessonBlocks, int colnum) {
        setWidth("152px");
        setClassName("column");
        setSpacing(false);

        System.out.println(lessonBlocks.size());
        if (!lessonBlocks.isEmpty()) {
            renderFilledColumn(weekday, lessonBlocks, colnum);
        } else {
			renderEmptyColumn(weekday, colnum);
        }
    }

    private void renderFilledColumn(String weekday, Deque<LessonBlock> lessonBlocks, int colnum) {
        AtomicInteger col = new AtomicInteger();
        ColumnLabel cl = new ColumnLabel(weekday);
        ArrayList<ColumnSegment> columnSegments = new ArrayList<>();
        for (int i = 0; i <= colnum; i++) {
			int index = 99999;
			Lesson lesson = null;
			if (!lessonBlocks.isEmpty()){
				LessonBlock block = lessonBlocks.peek();
				//Assuming all lessons don't start earlier than 8 am and don't end later than 5pm.
				//Yes, this is an awful solution, maybe I will figure out something better later.
				assert block != null;
				String time = block.getStartTime().split(":")[0];
				index = Integer.parseInt(time) - 8;
				lesson = block.getLesson();
			}
			ColumnSegment segment;
            if (index == i) {
				assert lesson != null;
				segment = new ScheduleSegment(
                        "#00FF00",
                        lesson.getSubject().getSubjectName(),
                        "1",
                        getTeacherShort(lesson.getTeacher())
                );
                lessonBlocks.pop();
            } else {
                index = i;
                segment = new ColumnSegment();
            }
            columnSegments.add(index, segment);
        }
        add(cl);
        add(columnSegments.toArray(new ColumnSegment[1]));
    }

	private void renderEmptyColumn(String weekday, int colnum){
		ColumnLabel cl = new ColumnLabel(weekday);
		ArrayList<ColumnSegment> columnSegments = new ArrayList<>();
		for(int i=0; i<=colnum; i++){
			columnSegments.add(new ColumnSegment());
		}
		add(cl);
		add(columnSegments.toArray(new ColumnSegment[1]));
	}

    private String getTeacherShort(Teacher teacher) {
        return (String.format("%s%s", teacher.getFirstName().charAt(0), teacher.getLastName().charAt(0))).toUpperCase(Locale.ROOT);
    }
}
