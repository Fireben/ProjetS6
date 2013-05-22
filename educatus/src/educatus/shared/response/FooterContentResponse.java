package educatus.shared.response;

public class FooterContentResponse extends AbstractResponse {

	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.FOOTER_CONTENT_RESPONSE;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}
}
