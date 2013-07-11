package educatus.shared.services.requestservice.request;

import educatus.shared.dto.exercice.ExerciceQuestion;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceQuestionValidationRequest extends AbstractRequest {

	private static final long serialVersionUID = 8746261569680572657L;

	private ExerciceQuestion exerciceQuestion;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_QUESTION_VALIDATION_REQUEST;
	}

	public ExerciceQuestion getExerciceQuestion() {
		return exerciceQuestion;
	}

	public void setExerciceQuestion(ExerciceQuestion exerciceQuestion) {
		this.exerciceQuestion = exerciceQuestion;
	}
}
