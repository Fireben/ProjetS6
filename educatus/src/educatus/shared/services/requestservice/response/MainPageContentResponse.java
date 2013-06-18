package educatus.shared.services.requestservice.response;

import educatus.shared.dto.MainPageContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class MainPageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 9074719652068698255L;
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.MAIN_PAGE_CONTENT_RESPONSE;

	private MainPageContent mainPageContent;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}

	public MainPageContent getMainPageContent() {
		return mainPageContent;
	}

	public void setMainPageContent(MainPageContent content) {
		this.mainPageContent = content;
	}
}