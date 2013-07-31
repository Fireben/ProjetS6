package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class StarDescriptionEntry extends Composite {
	private Label titleLabel;
	private FlowPanel starPanel;
	private final HorizontalPanel panel;

	public StarDescriptionEntry(String title, int difficulty) {
		super();
		panel = new HorizontalPanel();
		initWidget(panel);
		titleLabel = new Label(title + ": ");
		createStars(difficulty);
		initPanel();
	}

	private void createStars(int difficulty) {
		starPanel = new FlowPanel();
		difficulty = difficulty/2;
		for (int i = 0; i < difficulty+1; i++) {
			Image starImage = new Image("images/star.png");
			starImage.setSize("15px", "15px");
			starPanel.add(starImage);
		}		
	}

	private void initPanel() {
		titleLabel.setStyleName("descriptionTitle");
		panel.add(titleLabel);
		panel.add(starPanel);
		panel.setCellWidth(titleLabel, "120px");
		panel.setStyleName("seminaryTextEntry");
	}

	public void setStyleName(String styleName) {
		panel.setStyleName(styleName);
	}
}
