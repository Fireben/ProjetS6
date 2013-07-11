package educatus.shared.dto.exercice;

import java.util.ArrayList;
import java.util.List;

public class AnswerChoiceContent extends AbstractAnswerContent {

	public static enum AnswerChoiceType {
		SINGLE_CHOICE,
		MULTIPLE_CHOICE
	}
	
	private static final long serialVersionUID = -3431964655348780030L;

	private List<String> availableChoiceList = new ArrayList<String>();
	private List<String> answerList = new ArrayList<String>();
	private AnswerChoiceType answerChoiceType = AnswerChoiceType.SINGLE_CHOICE;

	@Override
	public ExerciceQuestionType getExerciceQuestionType() {
		return ExerciceQuestionType.ANSWER_CHOICE;
	}

	public List<String> getAvailableChoiceList() {
		return availableChoiceList;
	}

	public void setAvailableChoiceList(List<String> availableChoiceList) {
		this.availableChoiceList = availableChoiceList;
	}

	public AnswerChoiceType getType() {
		return answerChoiceType;
	}

	public void setType(AnswerChoiceType type) {
		this.answerChoiceType = type;
	}

	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
}
