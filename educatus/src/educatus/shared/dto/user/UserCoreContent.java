package educatus.shared.dto.user;

import java.io.Serializable;

public class UserCoreContent implements Serializable {

	private static final long serialVersionUID = 2890893100256899756L;
	
	private String cip;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String title;
	private String unity;
	private String room;
	private String fax;
	private String status;
	private String fullName;
	
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUnity() {
		return unity;
	}

	public void setUnity(String unity) {
		this.unity = unity;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
