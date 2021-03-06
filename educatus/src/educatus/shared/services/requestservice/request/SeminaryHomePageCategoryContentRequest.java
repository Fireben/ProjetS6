package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryHomePageCategoryContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -4696609413320649244L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_HOME_PAGE_CATEGORY_CONTENT_REQUEST;

	private CategoryCoreContent parentCategory = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public CategoryCoreContent getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(CategoryCoreContent parentCategory) {
		this.parentCategory = parentCategory;
	}
}
