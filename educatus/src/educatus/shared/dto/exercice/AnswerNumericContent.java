package educatus.shared.dto.exercice;

public class AnswerNumericContent extends AbstractAnswerContent {

	private static final long serialVersionUID = 8740611896224576313L;

	private int numericAnswer;
	
	@Override
	public ExerciceQuestionType getExerciceQuestionType() {
		return ExerciceQuestionType.ANSWER_NUMERIC;
	}

	public int getNumericAnswer() {
		return numericAnswer;
	}

	public void setNumericAnswer(int numericAnswer) {
		this.numericAnswer = numericAnswer;
	}
}
