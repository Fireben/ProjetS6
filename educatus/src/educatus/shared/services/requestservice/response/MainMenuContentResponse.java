package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class MainMenuContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 9074719652068698255L;	
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.MAIN_MENU_CONTENT_RESPONSE;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}
}