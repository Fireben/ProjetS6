package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryHomePageListingContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -5804623818759076508L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_HOME_PAGE_LISTING_CONTENT_REQUEST;

	private CategoryCoreContent selectedCategory = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public CategoryCoreContent getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryCoreContent selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
