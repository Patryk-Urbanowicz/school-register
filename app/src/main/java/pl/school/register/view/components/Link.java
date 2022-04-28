package pl.school.register.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.component.Component;

public class Link extends RouterLink{
	public Link(String url, Class<? extends Component> navigationTarget){
		super(url, navigationTarget);
			getStyle()
				.set("padding", "10px")
				.set("color", "#141414");
	}
}
