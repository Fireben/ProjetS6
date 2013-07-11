package educatus.shared.services.requestservice.response;

import educatus.shared.dto.exercice.ExerciceQuestion;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceQuestionValidationResponse extends AbstractResponse {

	private static final long serialVersionUID = 6905697088505448917L;

	private ExerciceQuestion exerciceQuestion;
	private boolean isValid;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_QUESTION_VALIDATION_RESPONSE;
	}

	public ExerciceQuestion getExerciceQuestion() {
		return exerciceQuestion;
	}

	public void setExerciceQuestion(ExerciceQuestion exerciceQuestion) {
		this.exerciceQuestion = exerciceQuestion;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
