package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceHomePageListingContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -7970337059231052049L;

	private CategoryCoreContent selectedCategory = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_HOME_PAGE_LISTING_CONTENT_REQUEST;
	}

	public CategoryCoreContent getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(CategoryCoreContent selectedCategory) {
		this.selectedCategory = selectedCategory;
	}
}
