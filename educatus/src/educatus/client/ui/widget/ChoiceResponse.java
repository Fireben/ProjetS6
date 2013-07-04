package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ChoiceResponse extends Composite {
	VerticalPanel panel;
	
	public ChoiceResponse() {
		super();
		panel = new VerticalPanel();
		initWidget(panel);
		Label questionLabel = new Label("Question");
		questionLabel.setStyleName("questionLabel");
		panel.add(questionLabel);
	}
	public void addChoice(String choiceText) {
		RadioButton radioButton = new RadioButton("response");
		radioButton.setText(" " + choiceText);
		radioButton.setStyleName("choiceResponseButton");
		panel.add(radioButton);
	}
}
