
package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class UserListingRequest extends AbstractRequest {

	private static final long serialVersionUID = -4445658798936956993L;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.USER_LISTING_REQUEST;
	}
}
