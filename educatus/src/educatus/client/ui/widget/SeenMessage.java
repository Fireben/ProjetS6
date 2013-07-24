package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class SeenMessage extends Composite {
	HorizontalPanel panel;
	
	public SeenMessage() {
		panel = new HorizontalPanel();
		initWidget(panel);		
		
		panel.setStyleName("center");
		Label label = new Label("You have already seen this seminar");
		label.setStyleName("seenLabel");
		panel.add(label);
		panel.add(new Image("images/ok.png"));		
	}

}
