package educatus.shared.dto.user;

import java.io.Serializable;

public class UserCoreContent implements Serializable {

	private static final long serialVersionUID = 2890893100256899756L;
	
	private String cip;
	private String firstName;
	private String lastName;
	private String email;
	
	private String joinedDate;
	private String lastConnexion;
	
	private Boolean privacyEnabled;
	
	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getLastConnexion() {
		return lastConnexion;
	}

	public void setLastConnexion(String lastConnexion) {
		this.lastConnexion = lastConnexion;
	}

	public Boolean getPrivacyEnabled() {
		return privacyEnabled;
	}

	public void setPrivacyEnabled(Boolean privacyEnabled) {
		this.privacyEnabled = privacyEnabled;
	}
}
