package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class CategoryAdministrationPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 8608209058559071854L;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_REQUEST;
	}
}