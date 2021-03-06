package educatus.shared.services.requestservice.response;

import educatus.shared.dto.HomePageContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class HomePageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -2105780267977463204L;
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.HOME_PAGE_CONTENT_RESPONSE;
	
	private HomePageContent homeContent;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}

	public HomePageContent getHomeContent() {
		return homeContent;
	}

	public void setHomeContent(HomePageContent homeContent) {
		this.homeContent = homeContent;
	}
}
