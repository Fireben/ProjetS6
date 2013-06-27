package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryAdministrationActionRequest extends AbstractRequest {

	public static enum SeminaryAdministractionAction {
		INSERT,
		DELETE,
		MODIFY
	}
	
	private static final long serialVersionUID = 3049588535764579312L;

	private SeminaryContent seminaryContent;
	private SeminaryAdministractionAction action = SeminaryAdministractionAction.INSERT;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.SEMINARY_ADMINISTRATION_ACTION_REQUEST;
	}

	public SeminaryContent getSeminaryContent() {
		return seminaryContent;
	}

	public void setSeminaryContent(SeminaryContent seminaryContent) {
		this.seminaryContent = seminaryContent;
	}

	public SeminaryAdministractionAction getAction() {
		return action;
	}

	public void setAction(SeminaryAdministractionAction action) {
		this.action = action;
	}
}
