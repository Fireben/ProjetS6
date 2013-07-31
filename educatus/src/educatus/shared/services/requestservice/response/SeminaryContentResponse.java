package educatus.shared.services.requestservice.response;

import educatus.shared.dto.seminary.SeminaryContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;

public class SeminaryContentResponse extends AbstractResponse {

	private static final long serialVersionUID = -7374501950617557773L;

	private SeminaryContent seminaryContent;
	private boolean isSeminaryCompletedByUser;

	@Override
	public ResponseTypeEnum GetResponseType() {
		return ResponseTypeEnum.SEMINARY_CONTENT_RESPONSE;
	}

	public SeminaryContent getSeminaryContent() {
		return seminaryContent;
	}

	public void setSeminaryContent(SeminaryContent seminaryContent) {
		this.seminaryContent = seminaryContent;
	}

	public boolean isSeminaryCompletedByUser() {
		return isSeminaryCompletedByUser;
	}

	public void setSeminaryCompletedByUser(boolean isSeminaryCompletedByUser) {
		this.isSeminaryCompletedByUser = isSeminaryCompletedByUser;
	}
}
