package educatus.shared.services.requestservice.response;

import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class LoginResponse extends AbstractResponse {

	public static enum LoginStatus {
		SUCCESS,
		FAILURE
	}

	private static final long serialVersionUID = -1783165567804879562L;

	// Provide secure way to return sessionID ?
	private String sessionID;
	private LoginStatus loginStatus = LoginStatus.FAILURE;
	private UserCoreContent userCoreContent = null;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.LOGIN_RESPONSE;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public LoginStatus getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}

	public UserCoreContent getUserCoreContent() {
		return userCoreContent;
	}

	public void setUserCoreContent(UserCoreContent userCoreContent) {
		this.userCoreContent = userCoreContent;
	}
}
