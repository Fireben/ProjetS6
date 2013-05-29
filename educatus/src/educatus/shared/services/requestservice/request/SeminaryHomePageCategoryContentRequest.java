package educatus.shared.services.requestservice.request;

import educatus.shared.businesslogic.dto.seminary.SeminaryHomeCategoryContent.CategoryContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryHomePageCategoryContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -4696609413320649244L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_HOME_PAGE_CATEGORY_CONTENT_REQUEST;

	private CategoryContent parentCategory = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public CategoryContent getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CategoryContent parentCategory) {
		this.parentCategory = parentCategory;
	}
}
