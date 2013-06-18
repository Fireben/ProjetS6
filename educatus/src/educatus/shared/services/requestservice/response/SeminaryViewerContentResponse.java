package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryViewerContentResponse extends AbstractResponse {

	private static final long serialVersionUID = 6780198980499684860L;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_VIEWER_CONTENT_RESPONSE;
	}
}
