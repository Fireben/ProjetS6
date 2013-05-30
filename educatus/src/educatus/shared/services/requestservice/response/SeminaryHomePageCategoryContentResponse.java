package educatus.shared.services.requestservice.response;

import educatus.shared.dto.seminary.SeminaryHomeCategoryContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryHomePageCategoryContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 4278444363266094172L;
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.SEMINARY_HOME_PAGE_CATEGORY_CONTENT_RESPONSE;

	private SeminaryHomeCategoryContent content = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}

	public SeminaryHomeCategoryContent getContent() {
		return content;
	}

	public void setContent(SeminaryHomeCategoryContent content) {
		this.content = content;
	}
}