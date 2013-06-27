package educatus.shared.services.requestservice.response;

import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryHomePageCategoryContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 4278444363266094172L;

	private SeminaryHomePageCategoryContent content = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_HOME_PAGE_CATEGORY_CONTENT_RESPONSE;
	}

	public SeminaryHomePageCategoryContent getContent() {
		return content;
	}

	public void setContent(SeminaryHomePageCategoryContent content) {
		this.content = content;
	}
}