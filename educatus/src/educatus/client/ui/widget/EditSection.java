package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import educatus.client.ui.CustomButton;

public class EditSection extends Composite {
	private FlowPanel rootPanel;
	protected FlowPanel panel;
	protected Label titleLabel;
	private CustomButton closeButton;
	
	public EditSection() {
		super();
		rootPanel = new FlowPanel();
		initWidget(rootPanel);
		createPanel();
	}

	private void createPanel() {
		createCloseButton();
		
		titleLabel = new Label();
		titleLabel.setStyleName("editSectionTitle");
		
		panel = new FlowPanel();
		panel.setStyleName("editSection");
		panel.add(closeButton);
		
		rootPanel.add(titleLabel);
		rootPanel.add(panel);
	}
	
	private void createCloseButton() {
		closeButton = new CustomButton();
		closeButton.add(new Image("images/closeButton.png"));
		closeButton.setStyleName("editCloseButton");
	}
	
	public CustomButton getCloseButton() {
		return closeButton;
	}
}
