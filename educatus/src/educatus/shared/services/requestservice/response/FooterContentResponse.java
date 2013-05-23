package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class FooterContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 988303966266893486L;
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.FOOTER_CONTENT_RESPONSE;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}
}
