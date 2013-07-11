package educatus.shared.services.requestservice.request;

import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceQuestionValidationRequest extends AbstractRequest {

	private static final long serialVersionUID = 8746261569680572657L;

	private ExerciceQuestionContent exerciceQuestion;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_QUESTION_VALIDATION_REQUEST;
	}

	public ExerciceQuestionContent getExerciceQuestion() {
		return exerciceQuestion;
	}

	public void setExerciceQuestion(ExerciceQuestionContent exerciceQuestion) {
		this.exerciceQuestion = exerciceQuestion;
	}
}
