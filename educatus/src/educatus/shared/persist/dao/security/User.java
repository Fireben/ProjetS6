package educatus.shared.persist.dao.security;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


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
	@Column(name="user_id")
	private Integer id;

	@Column(name="user_cip")
	private String cip;

	@Column(name="user_datadeleted")
	private Timestamp dateDeleted;

	@Column(name="user_datejoined")
	private Timestamp dateJoined;

	//bi-directional many-to-one association to Loguserconnection
	@OneToMany(mappedBy="user")
	private List<LogUserConnection> loguserconnections;

	//bi-directional many-to-many association to Permission
	@ManyToMany
	@JoinTable(
		name="userpermission"
		, joinColumns={
			@JoinColumn(name="user_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="perm_id")
			}
		)
	private List<Permission> permissions;

	//bi-directional many-to-many association to Usertype
	@ManyToMany(mappedBy="users")
	private List<UserType> usertypes;

	public User() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCip() {
		return this.cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public Timestamp getDateDeleted() {
		return this.dateDeleted;
	}

	public void setDateDeleted(Timestamp dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	public Timestamp getDateJoined() {
		return this.dateJoined;
	}

	public void setDateJoined(Timestamp dateJoined) {
		this.dateJoined = dateJoined;
	}

	public List<LogUserConnection> getLoguserconnections() {
		return this.loguserconnections;
	}

	public void setLoguserconnections(List<LogUserConnection> loguserconnections) {
		this.loguserconnections = loguserconnections;
	}

	public LogUserConnection addLoguserconnection(LogUserConnection loguserconnection) {
		getLoguserconnections().add(loguserconnection);
		loguserconnection.setUser(this);

		return loguserconnection;
	}

	public LogUserConnection removeLoguserconnection(LogUserConnection loguserconnection) {
		getLoguserconnections().remove(loguserconnection);
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

}