package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.CheckBox;

public abstract class ChoiceQuestion extends Question {
	public ChoiceQuestion(String question) {
		super(question);
	}
	
	public abstract void addChoice(String choiceText, String id);	
	
	public void uncheckAll() {
		CheckBox checkBox;
		for(int i = 0; i<panel.getWidgetCount(); i++) {
			if(panel.getWidget(i) instanceof CheckBox) {
				checkBox = (CheckBox)panel.getWidget(i);
				checkBox.setValue(false);
			}
		}
	}
}
