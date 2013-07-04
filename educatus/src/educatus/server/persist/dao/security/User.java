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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.Image;

@Entity
@NamedQueries({
	@NamedQuery(name = User.FIND_ALL, query = "SELECT u FROM User u"),
	@NamedQuery(name = User.FIND_BY_CIP, query = "SELECT u FROM User u WHERE u.cip=:cip") })
@Table(name = "security.users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "USER.FIND_ALL";
	public static final String FIND_BY_CIP = "USER.FIND_BY_CIP";
	public static final String FIND_BY_CIP_PARAM_NAME = "cip";
	
	@Id
	@SequenceGenerator(name = "USERS_USER_ID_GENERATOR", sequenceName = "security.users_user_id_seq", allocationSize = 1)
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
	@JoinTable(name = "security.userpermission", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "perm_id", nullable = false) })
	private List<Permission> associatedPermissionList;

	// bi-directional many-to-many association to Group
	@ManyToMany
	@JoinTable(name = "security.groupuser", joinColumns = { @JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "grou_id", nullable = false) })
	private List<Group> associatedGroupList;

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

	public List<Permission> getAssociatedPermissionList() {
		return this.associatedPermissionList;
	}

	public void setAssociatedPermissionList(List<Permission> associatedPermissionList) {
		this.associatedPermissionList = associatedPermissionList;
	}

	public List<Group> getAssociatedGroupList() {
		return this.associatedGroupList;
	}

	public void setAssociatedGroupList(List<Group> associatedGroupList) {
		this.associatedGroupList = associatedGroupList;
	}
}