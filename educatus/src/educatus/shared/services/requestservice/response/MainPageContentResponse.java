package educatus.shared.services.requestservice.response;

import educatus.shared.dto.MainPageContent;
import educatus.shared.dto.ViewModeEnum;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class MainPageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 9074719652068698255L;

	private ViewModeEnum viewMode;
	private MainPageContent mainPageContent;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.MAIN_PAGE_CONTENT_RESPONSE;
	}

	public MainPageContent getMainPageContent() {
		return mainPageContent;
	}

	public void setMainPageContent(MainPageContent content) {
		this.mainPageContent = content;
	}

	public ViewModeEnum getViewMode() {
		return viewMode;
	}

	public void setViewMode(ViewModeEnum viewMode) {
		this.viewMode = viewMode;
	}
}