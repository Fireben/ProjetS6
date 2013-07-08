package educatus.client.ui.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public class ResponseFeedback extends Composite {
	private ClickHandler clickHandler;
	private String message;
	private String imageUrl;
	private String buttonText;	
	private FlowPanel panel;
	private Button button;
	
	public ResponseFeedback(String message, String imageUrl, String buttonText, ClickHandler clickHandler) {
		this.message = message;
		this.imageUrl = imageUrl;
		this.buttonText = buttonText;
		this.clickHandler = clickHandler;
		
		panel = new FlowPanel();
		initWidget(panel);	
		
		createPanel();
	}
	private void createPanel() {
		panel.setStyleName("responseFeedbackPanel");
		
		Label label = new Label(message);
		label.setStyleName("responseFeedbackLabel");
		
		button = new Button(buttonText);
		button.addClickHandler(clickHandler);
		
		panel.add(new Image(imageUrl));
		panel.add(label);
		panel.add(button);
	}	
}
