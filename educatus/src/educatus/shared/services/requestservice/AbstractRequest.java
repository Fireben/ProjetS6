package educatus.shared.services.requestservice;

import java.io.Serializable;

public abstract class AbstractRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String sessionID = null;
	private String culture = null;
	private String language = null;

	public abstract RequestTypeEnum GetRequestType();

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public String getCulture() {
		return culture;
	}
	
	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
