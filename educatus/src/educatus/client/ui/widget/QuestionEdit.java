package educatus.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import educatus.client.ui.CustomButton;

public class QuestionEdit extends Composite {
	private FlowPanel panel;
	private TextBox questionBox;
	private FlowPanel buttonPanel;
	private Widget answer;
	private CustomButton closeButton;
	
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
		panel = new FlowPanel();
		initWidget(panel);
		createPanel();
	}

	public void createPanel() {
		createCloseButton();
		panel.add(closeButton);
		
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
	
	private void createCloseButton() {
		closeButton = new CustomButton();
		closeButton.add(new Image("images/closeButton.png"));
		closeButton.setStyleName("editCloseButton");
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

	public CustomButton getCloseButton() {
		return closeButton;
	}
}
