package educatus.client.ui.widget;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.CheckBox;

public class MultipleChoiceQuestion extends ChoiceQuestion {
	
	public MultipleChoiceQuestion(String question) {
		super(question);
	}
	
	public void addChoice(String choiceText, String id) {
		CheckBox checkBox = new CheckBox();
		checkBox.setText(" " + choiceText);
		checkBox.setStyleName("choiceResponseButton");
		checkBox.getElement().setId(id);
		panel.add(checkBox);
	}	
	
	public ArrayList<String> getValues() {
		ArrayList<String> checkedResponses = new ArrayList<String>();
		CheckBox checkBox;
		for(int i = 0; i<panel.getWidgetCount(); i++) {
			if(panel.getWidget(i) instanceof CheckBox) {
			checkBox = (CheckBox)panel.getWidget(i);
				if(checkBox.getValue()) {
					checkedResponses.add(checkBox.getElement().getId());
				}
			}
		}
		return checkedResponses;		
	}
}
