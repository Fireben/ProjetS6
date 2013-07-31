package educatus.client.ui.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

public class SearchBox extends Composite {
	HorizontalPanel panel;
	Image button;
	TextBox searchText;
	
	public SearchBox() {
		panel = new HorizontalPanel();
		initWidget(panel);
		createPanel();
	}
	
	private void createPanel() {
		panel.setStyleName("searchBox");
		searchText= new TextBox();
		searchText.setStyleName("searchText");
		panel.add(searchText);
		button = new Image("images/search.png");
		button.setStyleName("searchButton");
		panel.add(button);
	}
	
	public HandlerRegistration addClickHandler(ClickHandler clickHandler) {
		return button.addClickHandler(clickHandler);	
	}
	
	public String getValue() {
		return searchText.getValue();
	}

	public void clearText() {
		searchText.setValue(null);
	}
}
