package educatus.shared.services.requestservice.response;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class UserListingResponse extends AbstractResponse {

	private static final long serialVersionUID = -6469966531526519368L;

	private List<UserCoreContent> userCoreContentList = new ArrayList<UserCoreContent>();
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.USER_LISTING_RESPONSE;
	}

	public List<UserCoreContent> getUserCoreContentList() {
		return userCoreContentList;
	}

	public void setUserCoreContentList(List<UserCoreContent> userCoreContentList) {
		this.userCoreContentList = userCoreContentList;
	}

}
