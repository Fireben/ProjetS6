package educatus.client.ui.widget;

import com.google.gwt.user.client.ui.TextBox;


public class TextQuestion extends Question {
	private TextBox responseBox;
	
	public TextQuestion(String question) {
		super(question);
		responseBox = new TextBox();
		panel.add(responseBox);
	}
	
	public String getResponse() {
		return responseBox.getValue();
	}
	
	public void clearResponse() {
		responseBox = new TextBox();
	}
}
