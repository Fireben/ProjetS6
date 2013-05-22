package educatus.shared.request;

public class HomePageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -5292046859790488627L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.HOME_PAGE_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}
