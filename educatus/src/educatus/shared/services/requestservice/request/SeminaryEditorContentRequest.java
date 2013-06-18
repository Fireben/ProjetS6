package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class SeminaryEditorContentRequest extends AbstractRequest {

	private static final long serialVersionUID = 8608209058559071854L;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.SEMINARY_EDITOR_CONTENT_REQUEST;
	}
}
