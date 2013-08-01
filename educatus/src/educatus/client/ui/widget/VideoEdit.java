package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class VideoEdit extends EditSection{
	private HorizontalPanel hyperlinkPanel;
	private TextBox hyperlinkBox;
	private HorizontalPanel descriptionPanel;
	private TextBox descriptionBox;
	
	public VideoEdit() {
		super();		
		createPanel();
	}
	
	private void createHyperlink() {
		descriptionPanel = new HorizontalPanel();
		descriptionPanel.add(new Label("Description : "));
		descriptionBox = new TextBox();
		descriptionBox.setStyleName("editTitleTextBox");
		descriptionPanel.add(descriptionBox);		
		descriptionPanel.setStyleName("editTitle");
	}
	
	private void createDescription() {
		hyperlinkPanel = new HorizontalPanel();
		hyperlinkPanel.add(new Label("Hyperlink : "));
		hyperlinkBox = new TextBox();
		hyperlinkBox.setStyleName("editTitleTextBox");
		hyperlinkPanel.add(hyperlinkBox);		
		hyperlinkPanel.setStyleName("editTitle");
	}
	
	private void createPanel() {
		titleLabel.setText("Video");
		createHyperlink();
		createDescription();
		panel.add(descriptionPanel);
		panel.add(hyperlinkPanel);	
	}	
	
	public String getHyperlink() {
		return hyperlinkBox.getText();
	}
	
	public String getDescription() {
		return descriptionBox.getText();
	}
}
