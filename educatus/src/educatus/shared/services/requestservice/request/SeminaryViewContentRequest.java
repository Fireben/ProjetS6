package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.SeminaryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryViewContentRequest extends AbstractRequest {
	
	private static final long serialVersionUID = 5536613889247985828L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_VIEWER_CONTENT_REQUEST;
	
	private SeminaryCoreContent selectedSeminary = null;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public SeminaryCoreContent getSelectedSeminary() {
		return selectedSeminary;
	}

	public void setSelectedSeminary(SeminaryCoreContent selectedSeminary) {
		this.selectedSeminary = selectedSeminary;
	}
}
