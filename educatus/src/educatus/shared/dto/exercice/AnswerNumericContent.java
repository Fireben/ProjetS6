package educatus.shared.dto.exercice;

public class AnswerNumericContent extends AbstractAnswerContent {

	private static final long serialVersionUID = 8740611896224576313L;

	@Override
	public ExerciceQuestionType getExerciceQuestionType() {
		return ExerciceQuestionType.ANSWER_NUMERIC;
	}
}
