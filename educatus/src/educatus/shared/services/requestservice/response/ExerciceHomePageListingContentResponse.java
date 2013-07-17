package educatus.shared.services.requestservice.response;

import educatus.shared.dto.pagecontent.ExerciceHomePageListingContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceHomePageListingContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -8886820359785485453L;

	private ExerciceHomePageListingContent content = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_HOME_PAGE_LISTING_CONTENT_RESPONSE;
	}

	public ExerciceHomePageListingContent getContent() {
		return content;
	}

	public void setContent(ExerciceHomePageListingContent content) {
		this.content = content;
	}
}