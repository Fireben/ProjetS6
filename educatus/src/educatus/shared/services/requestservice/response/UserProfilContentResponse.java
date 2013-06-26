package educatus.shared.services.requestservice.response;

import educatus.shared.dto.user.UserProfilContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class UserProfilContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 8030280063344228522L;

	private UserProfilContent userProfilContent;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.PROFIL_PAGE_CONTENT_RESPONSE;
	}

	public UserProfilContent getUserProfilContent() {
		return userProfilContent;
	}

	public void setUserProfilContent(UserProfilContent userProfilContent) {
		this.userProfilContent = userProfilContent;
	}
}
