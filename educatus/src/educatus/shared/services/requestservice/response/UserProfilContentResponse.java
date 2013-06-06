package educatus.shared.services.requestservice.response;

import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class UserProfilContentResponse extends AbstractResponse {
	
	private static final long serialVersionUID = 8030280063344228522L;
	private static ResponseTypeEnum RESPONSE_TYPE = ResponseTypeEnum.PROFIL_PAGE_CONTENT_RESPONSE;

	private UserProfilContent userProfilContent;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return RESPONSE_TYPE;
	}

	public UserProfilContent getUserProfilContent() {
		return userProfilContent;
	}

	public void setUserProfilContent(UserProfilContent userProfilContent) {
		this.userProfilContent = userProfilContent;
	}
}
