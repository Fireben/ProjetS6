package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryEditorContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -1618028085456577118L;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_EDITOR_CONTENT_RESPONSE;
	}
}
