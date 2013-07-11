package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

import educatus.shared.dto.exercice.AnswerTextContent;

public class TextAnswerEdit extends Composite {
	FlowPanel panel;
	TextBox answer;
	
	public TextAnswerEdit() {
		panel = new FlowPanel();
		initWidget(panel);
		createPanel();
	}

	private void createPanel() {
		panel.setStyleName("textAnswerEdit");
		answer = new TextBox();
		panel.add(answer);
	}	
	
	public AnswerTextContent getAnswerTextContent() {
		AnswerTextContent answerTextContent = new AnswerTextContent();
		answerTextContent.setTextAnswer(answer.getText());
		return answerTextContent;
	}
}
