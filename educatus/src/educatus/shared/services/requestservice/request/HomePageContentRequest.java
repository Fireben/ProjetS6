package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class HomePageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -5292046859790488627L;

	private String language = null;
	private String culture = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.HOME_PAGE_CONTENT_REQUEST;
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
