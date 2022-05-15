package pl.school.register.view;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.RouterLayout;

@ParentLayout(value = AccountNestedLayout.class)
public class AccountMiddlePanelNestedLayout extends VerticalLayout implements RouterLayout {
    public AccountMiddlePanelNestedLayout(){
        setSizeFull();
        getStyle()
                .set("background", "#efefef")
                .set("margin", "10px");
        setHeight("100%");
    }
}
