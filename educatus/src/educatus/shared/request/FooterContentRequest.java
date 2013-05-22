package educatus.shared.request;

public class FooterContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -7555970829060472658L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.FOOTER_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}
