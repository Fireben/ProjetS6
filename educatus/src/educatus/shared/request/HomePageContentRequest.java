package educatus.shared.request;

public class HomePageContentRequest extends AbstractRequest {

	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.HOME_PAGE_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}
