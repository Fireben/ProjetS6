package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class QuestionEdit extends Composite {
	private FlowPanel panel;
	private TextBox questionBox;
	
	public QuestionEdit() {
		panel = new FlowPanel();
		initWidget(panel);
		createPanel();
	}
	
	public void createPanel() {
		panel.setStyleName("editSection");
		
		HorizontalPanel questionPanel = new HorizontalPanel();
		questionPanel.setStyleName("questionEdit");
		
		Label questionEditLabel = new Label("Question : ");
		questionEditLabel.setStyleName("questionEditLabel");		
		questionPanel.add(questionEditLabel);
		
		questionBox = new TextBox();
		questionBox.setStyleName("questionBox");
		questionPanel.add(questionBox);
		
		panel.add(questionPanel);
		
		Label answerEditLabel = new Label("Answer : ");
		answerEditLabel.setStyleName("answerEditLabel");
		panel.add(answerEditLabel);
		
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("questionEditButtonPanel");
		buttonPanel.add(new Button("Single Choice"));
		buttonPanel.add(new Button("Multiple Choices"));
		buttonPanel.add(new Button("Numeric"));
		panel.add(buttonPanel);		
	}
}
