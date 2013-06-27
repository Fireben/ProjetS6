package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryAdministrationActionResponse extends AbstractResponse {

	private static final long serialVersionUID = 4327786312837619280L;

	// TODO, return success, or preview ?
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_ADMINISTRATION_ACTION_RESPONSE;
	}
}
