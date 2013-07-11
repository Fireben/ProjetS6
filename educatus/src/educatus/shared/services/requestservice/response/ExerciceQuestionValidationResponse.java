package educatus.shared.services.requestservice.response;

import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceQuestionValidationResponse extends AbstractResponse {

	private static final long serialVersionUID = 6905697088505448917L;

	private ExerciceQuestionContent exerciceQuestion;
	private boolean isValid;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_QUESTION_VALIDATION_RESPONSE;
	}

	public ExerciceQuestionContent getExerciceQuestion() {
		return exerciceQuestion;
	}

	public void setExerciceQuestion(ExerciceQuestionContent exerciceQuestion) {
		this.exerciceQuestion = exerciceQuestion;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
