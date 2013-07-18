package educatus.shared.services.requestservice.request;

import educatus.shared.dto.pagecontent.ViewModeEnum;
import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class MainPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 5410345524705815167L;

	private ViewModeEnum viewMode = ViewModeEnum.GUEST;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.MAIN_PAGE_CONTENT_REQUEST;
	}
	
	public ViewModeEnum getViewMode() {
		return viewMode;
	}

	public void setViewMode(ViewModeEnum viewMode) {
		this.viewMode = viewMode;
	}
}