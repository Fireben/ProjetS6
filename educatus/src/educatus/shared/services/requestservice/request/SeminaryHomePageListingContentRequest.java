package educatus.shared.services.requestservice.request;

import educatus.shared.businesslogic.dto.seminary.SeminaryHomeCategoryContent.CategoryContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryHomePageListingContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -5804623818759076508L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.SEMINARY_HOME_PAGE_LISTING_CONTENT_REQUEST;

	private CategoryContent selectedCategory;

	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public CategoryContent getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryContent selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
