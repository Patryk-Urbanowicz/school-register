package pl.school.register.view;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import pl.school.register.view.components.*;

@ParentLayout(value = AccountMainLayout.class)
public class AccountNestedLayout extends HorizontalLayout implements RouterLayout {

    public AccountNestedLayout() {
        addClassName("main");
        setHeight("100%");
        add(createLeftPanel());
    }

    private VerticalLayout createLeftPanel() {
        VerticalLayout leftPanel = new VerticalLayout();
        leftPanel.setClassName("left-panel");
        leftPanel.setWidth("50%");
		Card choiceCard = new Card("200px");
		choiceCard.add(	
                new RouterLink("Schedule", StudentScheduleView.class),
                new RouterLink("Marks", StudentMarksView.class)
        );
        Card recent = new Card("300px");
        Label label = new Label("Recent");
        label.getStyle()
                .set("color", "black");
        recent.add(label);
//        leftPanel.setSpacing(true);
//        leftPanel.setJustifyContentMode(AROUND);
        leftPanel.add(choiceCard, recent);
        return leftPanel;
    }
}
