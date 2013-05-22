package educatus.shared.request;

public class MainMenuContentRequest extends AbstractRequest {

	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.MAIN_MENU_CONTENT_REQUEST;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}
}