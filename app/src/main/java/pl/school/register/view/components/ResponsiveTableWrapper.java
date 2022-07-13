package pl.school.register.view.components;

import com.vaadin.flow.component.html.Div;

public class ResponsiveTableWrapper extends Div {
    public ResponsiveTableWrapper(Div table){
        setClassName("tableFixedSize");
        add(table);
    }
}
