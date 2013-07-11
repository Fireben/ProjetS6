package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class ExerciceAdministrationActionResponse extends AbstractResponse {

	private static final long serialVersionUID = 7628179101074673780L;

	// TODO, return success, or preview ?
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.EXERCICE_ADMINISTRATION_ACTION_RESPONSE;
	}
}
