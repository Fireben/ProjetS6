package educatus.shared.response;

public class MainMenuContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 9074719652068698255L;	
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.MAIN_MENU_CONTENT_RESPONSE;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}
}