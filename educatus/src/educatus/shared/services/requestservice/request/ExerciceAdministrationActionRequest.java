package educatus.shared.services.requestservice.request;

import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceAdministrationActionRequest extends AbstractRequest {

	public static enum ExerciceAdministractionAction {
		INSERT,
		DELETE,
		MODIFY
	}

	private static final long serialVersionUID = 3049588535764579312L;

	private ExerciceContent seminaryContent;
	private ExerciceAdministractionAction action = ExerciceAdministractionAction.INSERT;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_ADMINISTRATION_ACTION_REQUEST;
	}

	public ExerciceAdministractionAction getAction() {
		return action;
	}

	public void setAction(ExerciceAdministractionAction action) {
		this.action = action;
	}

	public ExerciceContent getSeminaryContent() {
		return seminaryContent;
	}

	public void setSeminaryContent(ExerciceContent seminaryContent) {
		this.seminaryContent = seminaryContent;
	}
}
