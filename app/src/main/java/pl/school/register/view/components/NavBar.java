package pl.school.register.view.components;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;


@Tag("navbar")
public class NavBar extends Div {

    public NavBar(){
        Button logout = new Button("logout");
        HorizontalLayout horizontalLayout = new HorizontalLayout(logout);
        FlexLayout navLayout = new FlexLayout(horizontalLayout);
        navLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        setWidth("100%");
        getStyle().set("padding", "15px");
        add(navLayout);
    }
}
