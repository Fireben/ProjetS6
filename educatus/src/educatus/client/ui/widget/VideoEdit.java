package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class VideoEdit extends EditSection{
	private HorizontalPanel hyperlinkPanel;
	private TextBox hyperlinkBox;
	
	public VideoEdit() {
		super();		
		createPanel();
	}
	
	private void createHyperlink() {
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
		panel.add(hyperlinkPanel);	
	}	
	
	public String gethyperlink() {
		return hyperlinkBox.getText();
	}
}
