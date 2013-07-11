package educatus.shared.services.requestservice.request;

import educatus.shared.services.requestservice.AbstractRequest;
import educatus.shared.services.requestservice.RequestTypeEnum;

public class ExerciceAdministrationPageContentRequest extends AbstractRequest {

	private static final long serialVersionUID = -6616534747610915167L;

	@Override
	public RequestTypeEnum GetRequestType() {
		return RequestTypeEnum.EXERCICE_ADMINISTRATION_PAGE_CONTENT_REQUEST;
	}
}
