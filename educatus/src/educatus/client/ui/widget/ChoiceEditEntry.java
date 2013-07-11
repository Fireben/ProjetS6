package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.TextBox;

public class ChoiceEditEntry extends Composite {
	HorizontalPanel panel;
	CheckBox checkBox;
	TextBox choiceText;
	
	boolean multiple;
	
	public ChoiceEditEntry(boolean multiple)  {
		this.multiple = multiple;
		
		panel = new HorizontalPanel();
		initWidget(panel);
		createPanel();
	}
	
	private void createPanel() {
		panel.setSpacing(10);
		
		choiceText = new TextBox();
		choiceText.setStyleName("choiceText");
		panel.add(choiceText);
		
		if(multiple) {
			checkBox = new CheckBox();
			panel.add(checkBox);
		}
		else {
			checkBox = new RadioButton("choice");
			panel.add(checkBox);
		}
	}
	
	public boolean isSelected() {
		return checkBox.getValue();
	}
	
	public String getValue() {
		return choiceText.getText();
	}
}
