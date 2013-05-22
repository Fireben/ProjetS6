package educatus.shared.response;

public class HomePageContentResponse extends AbstractResponse {

	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.HOME_PAGE_CONTENT_RESPONSE;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}
}
