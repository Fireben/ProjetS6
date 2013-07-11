package educatus.shared.dto.exercice;

public class AnswerTextContent extends AbstractAnswerContent {

	private static final long serialVersionUID = 4605574335813473966L;

	private String textAnswer;
	
	@Override
	public ExerciceQuestionType getExerciceQuestionType() {
		return ExerciceQuestionType.ANSWER_TEXT;
	}

	public String getTextAnswer() {
		return textAnswer;
	}

	public void setTextAnswer(String textAnswer) {
		this.textAnswer = textAnswer;
	}
}
