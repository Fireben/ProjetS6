package educatus.shared.services.requestservice.response;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class CategoryAdministrationPageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -1618028085456577118L;

	private List<CategoryCoreContent> availableCategory = new ArrayList<CategoryCoreContent>();

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_RESPONSE;
	}

	public List<CategoryCoreContent> getCategoryCoreContentList() {
		return availableCategory;
	}

	public void setCategoryCoreContentList(List<CategoryCoreContent> categoryCoreContentList) {
		this.availableCategory = categoryCoreContentList;
	}
}
