package educatus.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class QuestionEdit extends EditSection {
	private TextBox questionBox;
	private FlowPanel buttonPanel;
	private Widget answer;
	
	private ClickHandler singleChoiceHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			panel.remove(buttonPanel);	
			answer = new ChoiceEdit(false);
			panel.add(answer);
		}
	};
	
	private ClickHandler multipleChoiceHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			panel.remove(buttonPanel);	
			answer = new ChoiceEdit(true);
			panel.add(answer);
		}
	};
	
	private ClickHandler numericHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			panel.remove(buttonPanel);	
			answer = new TextAnswerEdit();
			panel.add(answer);
		}
	};

	public QuestionEdit() {
		createPanel();
	}

	public void createPanel() {		
		panel.setStyleName("editSection");
		titleLabel.setText("Question");
		
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
		
		buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("answerEditPanel");
		
		Button singleChoiceButton = new Button("Single Choice");
		singleChoiceButton.setStyleName("answerEditButton");
		singleChoiceButton.addClickHandler(singleChoiceHandler);		
		buttonPanel.add(singleChoiceButton);
		
		Button multipleChoiceButton = new Button("Multiple Choices");
		multipleChoiceButton.setStyleName("answerEditButton");
		multipleChoiceButton.addClickHandler(multipleChoiceHandler);
		buttonPanel.add(multipleChoiceButton);		
		
		Button textButton = new Button("Text");
		textButton.setStyleName("answerEditButton");
		textButton.addClickHandler(numericHandler);
		buttonPanel.add(textButton);	
		
		panel.add(buttonPanel);		
	}
	
	public FlowPanel getPanel() {
		return panel;
	}
	
	public Widget getAnswer() {
		return answer;
	}
	
	public String getQuestion() {
		return questionBox.getValue();
	}
}
