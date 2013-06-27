package educatus.shared.services.requestservice;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sessionID = null;

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public abstract RequestTypeEnum GetRequestType();
}
