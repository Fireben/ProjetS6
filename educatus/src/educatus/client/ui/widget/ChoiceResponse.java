package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RadioButton;

public class ChoiceResponse extends Composite {
	FlowPanel panel;
	
	public ChoiceResponse() {
		super();
		panel = new FlowPanel();
		initWidget(panel);
	}
	public void addChoice(String choiceText) {
		RadioButton radioButton = new RadioButton("response");
		radioButton.setText(" " + choiceText);
		panel.add(radioButton);
		panel.add(new HTML("<br/>"));
	}
}
