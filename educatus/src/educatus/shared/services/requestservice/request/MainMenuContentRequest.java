package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class MainMenuContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 5410345524705815167L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.MAIN_MENU_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}