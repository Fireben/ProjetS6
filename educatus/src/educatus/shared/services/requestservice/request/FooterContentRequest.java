package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class FooterContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -7555970829060472658L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.FOOTER_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}
