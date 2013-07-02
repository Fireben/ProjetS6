package educatus.server.businesslogic;

public class LDAPUser {
	
	private String cip;
	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private String title;
	private String unity;
	private String room;
	private String fax;
	private String status;
	private String fullName;
	private boolean isAdmin = false;
	private boolean isClient = false;
	private String cipAdmin;

	public LDAPUser() {

	}

	public LDAPUser(String cip) {
		this.cip = cip;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setCourriel(String email) {
		this.email = email;
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

	public void setTitre(String title) {
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

	// Propriétés non prises en compte
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isClient() {
		return isClient;
	}

	public void setClient(boolean isClient) {
		this.isClient = isClient;
	}

	public String getCipAdmin() {
		return cipAdmin;
	}

	public void setCipAdmin(String cipAdmin) {
		this.cipAdmin = cipAdmin;
	}

	@Override
	public String toString() {
		String s = "";
		s += this.getCip() + " \n";
		s += this.getCipAdmin() + " \n";
		s += this.getTitle() + " \n";
		s += this.getFullName() + " \n";
		s += this.getLastName() + " \n";
		s += this.getFirstName() + " \n";
		s += this.getEmail() + " \n";
		s += this.getPhone() + " \n";
		s += this.getFax() + " \n";
		s += this.getRoom() + " \n";
		s += this.getStatus() + " \n";
		s += this.getUnity() + " \n";
		s += "isClient: " + this.isClient() + " \n";
		s += "isAdmin:  " + this.isAdmin() + " \n";
		s += "Admin: " + this.cipAdmin + "\n";

		return s + "\n\n";
	}

	public boolean equals(Object o) {
		if (this.getCip() == null)
			return false;
		if (o instanceof LDAPUser) {
			LDAPUser e = (LDAPUser) o;
			if (e.getCip() != null && this.getCip().equals(e.getCip()))
				return true;
		}
		return false;
	}

}
