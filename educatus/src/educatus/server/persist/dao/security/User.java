package educatus.server.persist.dao.security;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.Image;

/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name = "security.user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERS_USER_ID_GENERATOR", sequenceName = "security.users_user_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_USER_ID_GENERATOR")
	@Column(name = "user_id")
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "imag_avatar")
	private Image avatar;
	
	@Column(name = "user_cip", nullable = false, length = 8)
	private String cip;

	@Column(name = "user_datadeleted")
	private Timestamp dataDeleted;

	@Column(name = "user_datejoined", nullable = false)
	private Timestamp dateJoined;

	@Column(name = "user_lastname", length = 50)
	private String lastName;

	@Column(name = "user_name", length = 50)
	private String firstName;

	@Column(name = "user_profileprivacy", nullable = false)
	private Boolean profilePrivacy;

	// bi-directional many-to-one association to LogUserConnection
	@OneToMany(mappedBy = "user")
	private List<LogUserConnection> logUserConnections;

	// bi-directional many-to-many association to Permission
	@ManyToMany
	@JoinTable(name = "userpermission", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "perm_id", nullable = false) })
	private List<Permission> permissions;

	// bi-directional many-to-many association to UserType
	@ManyToMany(mappedBy = "users")
	private List<UserType> usertypes;

	// bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(name = "groupuser", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "grou_id", nullable = false) })
	private List<Group> groups;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Image getAvatar() {
		return this.avatar;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}

	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public Timestamp getDateDeleted() {
		return this.dataDeleted;
	}

	public void setDataDeleted(Timestamp dataDeleted) {
		this.dataDeleted = dataDeleted;
	}

	public Timestamp getDateJoined() {
		return this.dateJoined;
	}

	public void setDateJoined(Timestamp dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Boolean getProfilePrivacy() {
		return this.profilePrivacy;
	}

	public void setProfilePrivacy(Boolean profilPrivacy) {
		this.profilePrivacy = profilPrivacy;
	}

	public List<LogUserConnection> getLogUserConnections() {
		return this.logUserConnections;
	}

	public void setLogUserConnections(List<LogUserConnection> logUserConnections) {
		this.logUserConnections = logUserConnections;
	}

	public LogUserConnection addLogUserConnection(LogUserConnection loguserconnection) {
		getLogUserConnections().add(loguserconnection);
		loguserconnection.setUser(this);
		return loguserconnection;
	}

	public LogUserConnection removeLoguserconnection(LogUserConnection loguserconnection) {
		getLogUserConnections().remove(loguserconnection);
		loguserconnection.setUser(null);
		return loguserconnection;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<UserType> getUsertypes() {
		return this.usertypes;
	}

	public void setUsertypes(List<UserType> usertypes) {
		this.usertypes = usertypes;
	}

	public List<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
}