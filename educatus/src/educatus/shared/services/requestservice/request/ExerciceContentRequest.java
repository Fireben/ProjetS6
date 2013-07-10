package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 1511063311781002031L;

	private int selectedExerciceId;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_CONTENT_REQUEST;
	}

	public int getSelectedExerciceId() {
		return selectedExerciceId;
	}

	public void setSelectedExerciceId(int selectedExerciceId) {
		this.selectedExerciceId = selectedExerciceId;
	}

}
