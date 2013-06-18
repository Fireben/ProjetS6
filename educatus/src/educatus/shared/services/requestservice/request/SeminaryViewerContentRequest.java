package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryViewerContentRequest extends AbstractRequest {
	
	private static final long serialVersionUID = 5536613889247985828L;
	
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_VIEWER_CONTENT_REQUEST;
		
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}
