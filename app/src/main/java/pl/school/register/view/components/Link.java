package pl.school.register.view.components;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.Component;

public class Link extends RouterLink{
	public Link(String url, Class<? extends Component> navigationTarget){
		super(url, navigationTarget);
		setClassName("link");
	}
}
