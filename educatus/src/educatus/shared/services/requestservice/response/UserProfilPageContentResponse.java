package educatus.shared.services.requestservice.response;

import educatus.shared.dto.pagecontent.UserProfilPageContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class UserProfilPageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 8030280063344228522L;

	private UserProfilPageContent content;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.PROFIL_PAGE_CONTENT_RESPONSE;
	}

	public UserProfilPageContent getContent() {
		return content;
	}

	public void setContent(UserProfilPageContent content) {
		this.content = content;
	}
}
