package educatus.shared.services.requestservice.request;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class CategoryAdministrationPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 8608209058559071854L;

	private List<CategoryCoreContent> categoryCoreContentList = new ArrayList<CategoryCoreContent>();
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_REQUEST;
	}
	
	public List<CategoryCoreContent> getCategoryCoreContentList() {
		return categoryCoreContentList;
	}

	public void setCategoryCoreContentList(List<CategoryCoreContent> categoryCoreContentList) {
		this.categoryCoreContentList = categoryCoreContentList;
	}
}
