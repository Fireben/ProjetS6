package educatus.shared.services.requestservice.response;

import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class UserContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 8345601672451226280L;

	private UserProfilContent userProfilContent;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.USER_CONTENT_REQUEST;
	}

	public UserProfilContent getUserProfilContent() {
		return userProfilContent;
	}

	public void setUserProfilContent(UserProfilContent userProfilContent) {
		this.userProfilContent = userProfilContent;
	}
}
