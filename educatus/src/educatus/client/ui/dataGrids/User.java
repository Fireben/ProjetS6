package educatus.client.ui.dataGrids;

public class User {
	private String cip;
	private String firstName;
	private String LastName;	
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
	
	public User(String cip, String firstName, String LastName, String email,
			String phone, String title, String unity, String room, String fax,
			String status, String fullName, String joinedDate, String lastConnexion) {
		super();
		this.cip = cip;
		this.firstName = firstName;
		this.LastName = LastName;
		this.email = email;
		this.phone = phone;
		this.title = title;
		this.unity = unity;
		this.room = room;
		this.fax = fax;
		this.status = status;
		this.fullName = fullName;
		this.joinedDate = joinedDate;
		this.lastConnexion = lastConnexion;

	}

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
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
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

}
