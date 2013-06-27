package educatus.shared.services.requestservice.response;

import educatus.shared.dto.pagecontent.SeminaryHomePageListingContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryHomePageListingContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -8886820359785485453L;

	private SeminaryHomePageListingContent content = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_HOME_PAGE_LISTING_CONTENT_RESPONSE;
	}

	public SeminaryHomePageListingContent getContent() {
		return content;
	}

	public void setContent(SeminaryHomePageListingContent content) {
		this.content = content;
	}
}