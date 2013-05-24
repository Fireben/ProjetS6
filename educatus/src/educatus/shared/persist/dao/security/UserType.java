package educatus.shared.persist.dao.security;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import educatus.shared.persist.dao.internationalization.TextContentEntry;

/**
 * The persistent class for the usertype database table.
 * 
 */
@Entity
@Table(name = "security.usertype")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "usty_id")
	private Integer ustyId;

	@Column(name = "tece_description")
	private Integer teceDescription;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name")
	private TextContentEntry textContentEntry;

	// bi-directional many-to-many association to Permission
	@ManyToMany
	@JoinTable(name = "usertypepermission", joinColumns = { @JoinColumn(name = "usty_id") }, inverseJoinColumns = { @JoinColumn(name = "perm_id") })
	private List<Permission> permissions;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "userusertype", joinColumns = { @JoinColumn(name = "usty_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private List<User> users;

	public UserType() {
	}

	public Integer getUstyId() {
		return this.ustyId;
	}

	public void setUstyId(Integer ustyId) {
		this.ustyId = ustyId;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}

	public TextContentEntry getTextContentEntry() {
		return this.textContentEntry;
	}

	public void setTextContentEntry(TextContentEntry textContentEntry) {
		this.textContentEntry = textContentEntry;
	}

	public List<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}