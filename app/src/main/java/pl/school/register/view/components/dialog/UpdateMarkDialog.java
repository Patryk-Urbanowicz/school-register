package pl.school.register.view.components.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import pl.school.register.model.Mark;


public class UpdateMarkDialog extends Dialog {
    private Button confirm, cancel;
    private Mark data;
    public UpdateMarkDialog(){
        confirm = new Button("Confirm");
        cancel = new Button("Cancel", e -> this.close());
    }

    private VerticalLayout prepareLayout(){
        VerticalLayout layout = new VerticalLayout();
        Select<Integer> select = new Select<>();
        select.setItems(6,5,4,3,2,1);
        select.setValue(data.getValue());
        select.addValueChangeListener(listener -> {
           data.setValue(select.getValue());
        });
        Label why = new Label(data.getLabel());
        Label who = new Label(String.format("%s %s", data.getStudent().getFirstName(), data.getStudent().getLastName()));
        layout.add(why, who, select);
        return layout;
    }

    public void setData(Mark data){
        this.data = data;
    }

    public void setOnConfirmAction(ComponentEventListener<ClickEvent<Button>> listener){
        confirm.addClickListener(listener);
    }

    public void build(){
        VerticalLayout layout = prepareLayout();
        add(layout);
        getFooter().add(cancel, confirm);
    }

}
