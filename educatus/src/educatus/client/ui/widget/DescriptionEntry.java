package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class DescriptionEntry extends Composite {
	private Label titleLabel;
	private Label textLabel;
	private final HorizontalPanel panel;
	
	public DescriptionEntry(String title, String text) {
		super();
		panel = new HorizontalPanel();
		initWidget(panel);
		titleLabel = new Label(title + ": ");
		textLabel = new Label(text);
		InitPanel();
	}	
	
	private void InitPanel() {
		titleLabel.setStyleName("descriptionTitle");
		panel.add(titleLabel);
		panel.setCellWidth(titleLabel, "120px");
		panel.add(textLabel);
		panel.setCellWidth(textLabel, "160px");
		panel.setStyleName("seminaryTextEntry");
	}
	
	public void setStyleName(String styleName) {
		panel.setStyleName(styleName);
	}
}
