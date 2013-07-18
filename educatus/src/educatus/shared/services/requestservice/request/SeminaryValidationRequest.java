package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryValidationRequest extends AbstractRequest {

	private static final long serialVersionUID = 8881124129613668130L;

	private int seminaryId;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.SEMINARY_VALIDATION_REQUEST;
	}

	public int getSeminaryId() {
		return seminaryId;
	}

	public void setSeminaryId(int seminaryId) {
		this.seminaryId = seminaryId;
	}
}
