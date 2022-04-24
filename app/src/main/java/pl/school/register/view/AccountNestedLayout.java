package pl.school.register.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import static com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode.*;

@ParentLayout(value = AccountMainLayout.class)
public class AccountNestedLayout extends HorizontalLayout implements RouterLayout {

    public AccountNestedLayout() {
        getStyle().set("height", "100%");
        add(createLeftPanel());
    }

    private VerticalLayout createLeftPanel() {
        VerticalLayout leftPanel = new VerticalLayout();
        VerticalLayout choiceCard = new VerticalLayout(
                new RouterLink("Schedule", StudentScheduleView.class),
                new RouterLink("Marks", StudentMarksView.class)
        );
//        Button tempButton1 = new Button("Grades");
//        Button tempButton2 = new Button("Marks");

//        tempButton1.addThemeVariants(ButtonVariant.MATERIAL_OUTLINED);
//        tempButton2.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        choiceCard.add(tempButton1, tempButton2);
        choiceCard.getStyle()
                .set("padding", "10px")
                .set("background", "white")
                .set("border", "1px solid grey");
        choiceCard.setHeight("200px");

        VerticalLayout recent = new VerticalLayout();
        Label label = new Label("Recent");
        label.getStyle()
                .set("color", "black");

        recent.add(label);
        recent.getStyle()
                .set("padding", "10px")
                .set("background", "white")
                .set("border", "1px solid grey");
        recent.setHeight("300px");

        leftPanel.setSpacing(true);
        leftPanel.setJustifyContentMode(AROUND);
        leftPanel.add(choiceCard, recent);
        leftPanel.getStyle()
                .set("width", "50%");
        return leftPanel;
    }
}
