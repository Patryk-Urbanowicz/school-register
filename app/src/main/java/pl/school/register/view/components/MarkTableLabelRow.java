package pl.school.register.view.components;

public class MarkTableLabelRow extends Row{
    public MarkTableLabelRow(){
        add(new ColumnLabel("Subjects"),
                new ColumnLabel("Marks"));
    }
}
