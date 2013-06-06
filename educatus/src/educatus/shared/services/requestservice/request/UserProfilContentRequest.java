package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class UserProfilContentRequest extends AbstractRequest {
	
	private static final long serialVersionUID = 1057230778906255977L;
	private static RequestTypeEnum REQUEST_TYPE = RequestTypeEnum.PROFIL_PAGE_CONTENT_REQUEST;

	private String userCip;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return REQUEST_TYPE;
	}

	public String getUserCip() {
		return userCip;
	}

	public void setUserCip(String userCip) {
		this.userCip = userCip;
	}
}
