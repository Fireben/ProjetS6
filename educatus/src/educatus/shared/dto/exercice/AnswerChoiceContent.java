package educatus.shared.dto.exercice;

import java.util.ArrayList;
import java.util.List;

public class AnswerChoiceContent extends AbstractAnswerContent {

	private static final long serialVersionUID = -3431964655348780030L;

	private List<String> availableChoiceList = new ArrayList<String>();

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
}
