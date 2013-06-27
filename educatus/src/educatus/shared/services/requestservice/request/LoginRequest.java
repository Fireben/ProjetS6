package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class LoginRequest extends AbstractRequest {

	private static final long serialVersionUID = 5601619707524331991L;

	// TODO, Provide secure way to transfert credentials (HTTPS)
	private String userName;
	private String password;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.LOGIN_REQUEST;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
