package educatus.client.ui.widget;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import educatus.shared.dto.exercice.AnswerChoiceContent;
import educatus.shared.dto.exercice.AnswerChoiceContent.AnswerChoiceType;

public class ChoiceEdit extends Composite {
	VerticalPanel panel;
	boolean multiple;
	
	private ClickHandler addChoiceHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			panel.add(new ChoiceEditEntry(multiple));			
		}
	};
	
	public ChoiceEdit(boolean multiple)  {
		this.multiple = multiple;
		
		panel = new VerticalPanel();
		initWidget(panel);
		createPanel();
	}
	
	private void createPanel() {
		panel.setStyleName("answerEditPanel");
		Button addButton = new Button("Add Choice");
		addButton.setStyleName("answerEditButton");
		addButton.addClickHandler(addChoiceHandler);
		panel.add(addButton);
	}
	
	public boolean isMultiple() {
		return multiple;
	}
	
	public AnswerChoiceContent getAnswerChoiceContent() {
		AnswerChoiceContent answerChoiceContent = new AnswerChoiceContent();
		ArrayList<String> answerList = new ArrayList<String>();
		ArrayList<String> availableChoiceList = new ArrayList<String>();
		
		for(int i = 0; i<panel.getWidgetCount(); i++) {
			if(panel.getWidget(i) instanceof ChoiceEditEntry) {
				ChoiceEditEntry choiceEditEntry = (ChoiceEditEntry)panel.getWidget(i);
				if(choiceEditEntry.isSelected()) {
					answerList.add(String.valueOf(i));
				}
				availableChoiceList.add(choiceEditEntry.getValue());
			}
		}
		answerChoiceContent.setAnswerList(answerList);
		answerChoiceContent.setAvailableChoiceList(availableChoiceList);
		AnswerChoiceType type = multiple ? AnswerChoiceType.MULTIPLE_CHOICE : AnswerChoiceType.SINGLE_CHOICE;
		answerChoiceContent.setType(type);
		return answerChoiceContent;
	}
}
