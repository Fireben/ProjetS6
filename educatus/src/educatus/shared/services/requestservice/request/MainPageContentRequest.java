package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class MainPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 5410345524705815167L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.MAIN_PAGE_CONTENT_REQUEST;

	private String language = null;
	private String culture = null;

	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
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