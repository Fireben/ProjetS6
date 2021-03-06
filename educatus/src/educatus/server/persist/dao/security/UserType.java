package educatus.server.persist.dao.security;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@Table(name = "security.usertype")
public class UserType implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "USERTYPE_USTY_ID_GENERATOR", sequenceName = "security.usertype_usty_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERTYPE_USTY_ID_GENERATOR")
	@Column(name = "usty_id")
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description")
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name")
	private TextContentEntry name;

	// bi-directional many-to-many association to Permission
	@ManyToMany
	@JoinTable(name = "usertypepermission", joinColumns = { @JoinColumn(name = "usty_id") }, inverseJoinColumns = { @JoinColumn(name = "perm_id") })
	private List<Permission> associatedPermissionList;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "userusertype", joinColumns = { @JoinColumn(name = "usty_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	private List<User> associatedUserList;

	// bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(name = "groupusertype", joinColumns = { @JoinColumn(name = "usty_id") }, inverseJoinColumns = { @JoinColumn(name = "grou_id") })
	private List<Group> associatedGroupList;

	public UserType() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TextContentEntry getDescription() {
		return this.description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public TextContentEntry getName() {
		return this.name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}

	public List<Permission> getAssociatedPermissionList() {
		return this.associatedPermissionList;
	}

	public void setAssociatedPermissionList(List<Permission> associatedPermissionList) {
		this.associatedPermissionList = associatedPermissionList;
	}

	public List<User> getAssociatedUserList() {
		return this.associatedUserList;
	}

	public void setAssociatedUserList(List<User> associatedUserList) {
		this.associatedUserList = associatedUserList;
	}

	public List<Group> getAssociatedGroupList() {
		return associatedGroupList;
	}

	public void setAssociatedGroupList(List<Group> associatedGroupList) {
		this.associatedGroupList = associatedGroupList;
	}
}