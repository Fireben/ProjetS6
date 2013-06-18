package educatus.shared.dto.user;

import java.io.Serializable;
import java.sql.Date;

public class UserCoreContent implements Serializable {

	private static final long serialVersionUID = 2890893100256899756L;
	
	private String cip;
	private String firstName;
	private String lastName;
	private String email;
	
	private Date joinedDate;
	private Date lastConnexion;
	
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

	public Date getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}

	public Date getLastConnexion() {
		return lastConnexion;
	}

	public void setLastConnexion(Date lastConnexion) {
		this.lastConnexion = lastConnexion;
	}

	public Boolean getPrivacyEnabled() {
		return privacyEnabled;
	}

	public void setPrivacyEnabled(Boolean privacyEnabled) {
		this.privacyEnabled = privacyEnabled;
	}
}
