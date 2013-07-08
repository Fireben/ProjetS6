package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Question extends Composite {
	protected VerticalPanel panel;
	
	public Question(String question) {
		super();
		panel = new VerticalPanel();
		initWidget(panel);
		
		Label questionTitle = new Label("Question");
		questionTitle.setStyleName("questionTitle");
		panel.add(questionTitle);
		
		Label questionLabel = new Label(question);
		questionLabel.setStyleName("questionLabel");
		panel.add(questionLabel);
	}
}
