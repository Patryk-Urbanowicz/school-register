package pl.school.register.view.components.dialog;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import pl.school.register.model.Mark;


public class NewMarkDialog extends Dialog {
    private Button confirm, cancel;
    private Mark data;
    public NewMarkDialog(){
        confirm = new Button("Confirm");
        cancel = new Button("Cancel", e -> this.close());
    }

    private VerticalLayout prepareLayout(){
        VerticalLayout layout = new VerticalLayout();
        Select<Integer> valueSelect = new Select<>();
        IntegerField weightField = new IntegerField();
        TextField label = new TextField();
        TextField description = new TextField();

        label.setLabel("Mark label");
        label.setMinLength(1);
        label.setMaxLength(255);
        label.addValueChangeListener(listener -> {
            data.setLabel(label.getValue());
        });

        description.setLabel("Description");
        description.setMinLength(1);
        description.setMaxLength(255);
        description.addValueChangeListener(listener -> {
            data.setLabel(description.getValue());
        });

        valueSelect.setItems(6,5,4,3,2,1);
        valueSelect.setValue(3);
        data.setValue(3);
        valueSelect.setLabel("Mark");
        valueSelect.addValueChangeListener(listener -> {
            data.setValue(valueSelect.getValue());
        });

        weightField.setLabel("Mark weight");
        weightField.setMin(0);
        weightField.addValueChangeListener(listener -> {
            data.setWeight(weightField.getValue());
        });

        Label why = new Label(data.getLabel());
        Label who = new Label(String.format("%s %s", data.getStudent().getFirstName(), data.getStudent().getLastName()));
        layout.add(why, who, label, description, valueSelect, weightField, valueSelect);
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
