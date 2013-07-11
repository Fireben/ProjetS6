package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceAdministrationPageContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -6244164083309962390L;
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_ADMINISTRATION_CONTENT_RESPONSE;
	}
}
