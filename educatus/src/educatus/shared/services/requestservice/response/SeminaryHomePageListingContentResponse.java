package educatus.shared.services.requestservice.response;

import educatus.shared.dto.seminary.SeminaryHomeListingContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryHomePageListingContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -8886820359785485453L;

	private SeminaryHomeListingContent content = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_HOME_PAGE_LISTING_CONTENT_RESPONSE;
	}

	public SeminaryHomeListingContent getContent() {
		return content;
	}

	public void setContent(SeminaryHomeListingContent content) {
		this.content = content;
	}
}