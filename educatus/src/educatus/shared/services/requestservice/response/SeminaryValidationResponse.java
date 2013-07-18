package educatus.shared.services.requestservice.response;

import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryValidationResponse extends AbstractResponse {

	private static final long serialVersionUID = -6732123937604813321L;

	private boolean isValid = false; 
	
	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_VALIDATION_RESPONSE;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}
