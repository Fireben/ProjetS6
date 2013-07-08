package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.RadioButton;

public class SingleChoiceQuestion extends ChoiceQuestion{
	public SingleChoiceQuestion(String question) {
		super(question);
	}
	
	public String getValue() {
		RadioButton radioButton;
		for(int i = 0; i<panel.getWidgetCount(); i++) {
			if(panel.getWidget(i) instanceof RadioButton) {
			radioButton = (RadioButton)panel.getWidget(i);
				if(radioButton.getValue()) {
					return radioButton.getElement().getId();
				}
			}
		}
		return null;
	}
	
	public void addChoice(String choiceText, String id) {
		RadioButton radioButton = new RadioButton("response");
		radioButton.setText(" " + choiceText);
		radioButton.getElement().setId(id);
		radioButton.setStyleName("choiceResponseButton");
		panel.add(radioButton);
	}	
}
