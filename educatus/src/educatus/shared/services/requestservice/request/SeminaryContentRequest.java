package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -6213239064915985024L;

	private int selectedSeminaryId;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.SEMINARY_CONTENT_REQUEST;
	}
	
	public int getSelectedSeminaryId() {
		return selectedSeminaryId;
	}

	public void setSelectedSeminaryId(int selectedSeminaryId) {
		this.selectedSeminaryId = selectedSeminaryId;
	}
}
