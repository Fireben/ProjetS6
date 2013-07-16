package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class UserContentRequest extends AbstractRequest {
	
	private static final long serialVersionUID = -239366714076806288L;
	private String requestedUser;
	
	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.USER_CONTENT_REQUEST;
	}

	public String getRequestedUser() {
		return requestedUser;
	}

	public void setRequestedUser(String requestedUser) {
		this.requestedUser = requestedUser;
	}
}
