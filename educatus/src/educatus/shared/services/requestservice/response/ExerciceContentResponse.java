package educatus.shared.services.requestservice.response;

import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 2541691256455044067L;

	private ExerciceContent exerciceContent;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_CONTENT_RESPONSE;
	}

	public ExerciceContent getExerciceContent() {
		return exerciceContent;
	}

	public void setExerciceContent(ExerciceContent exerciceContent) {
		this.exerciceContent = exerciceContent;
	}
}
