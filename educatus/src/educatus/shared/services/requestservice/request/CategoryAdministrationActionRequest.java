package educatus.shared.services.requestservice.request;

import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class CategoryAdministrationActionRequest extends AbstractRequest {

	public static enum CategoryAdministractionAction {
		INSERT,
		DELETE,
		MODIFY
	}

	private static final long serialVersionUID = 7039197815577863374L;

	private CategoryCoreContent categoryCoreContent;
	private CategoryAdministractionAction action = CategoryAdministractionAction.INSERT;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_ADMINISTRATION_ACTION_REQUEST;
	}

	public CategoryCoreContent getCategoryCoreContent() {
		return categoryCoreContent;
	}

	public void setCategoryCoreContent(CategoryCoreContent categoryCoreContent) {
		this.categoryCoreContent = categoryCoreContent;
	}

	public CategoryAdministractionAction getAction() {
		return action;
	}

	public void setAction(CategoryAdministractionAction action) {
		this.action = action;
	}
}
