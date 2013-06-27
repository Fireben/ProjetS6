package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class CategoryAdministrationPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 8608209058559071854L;

	private String language;
	private String culture;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.CATEGORY_ADMINISTRATION_PAGE_CONTENT_REQUEST;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}
}
