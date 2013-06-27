package educatus.shared.services.requestservice.response;

import educatus.shared.dto.pagecontent.HomePageContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class HomePageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -2105780267977463204L;

	private HomePageContent homeContent;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.HOME_PAGE_CONTENT_RESPONSE;
	}

	public HomePageContent getHomeContent() {
		return homeContent;
	}

	public void setHomeContent(HomePageContent homeContent) {
		this.homeContent = homeContent;
	}
}
