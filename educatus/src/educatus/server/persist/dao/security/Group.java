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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.TextContentEntry;

@Entity
@Table(name = "security.groups")
public class Group implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "GROUPS_GROUID_GENERATOR", sequenceName = "security.groups_grou_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GROUPS_GROUID_GENERATOR")
	@Column(name = "grou_id", unique = true, nullable = false)
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
	@ManyToMany(mappedBy = "associatedGroupList")
	private List<Permission> associatedPermissionList;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "associatedGroupList")
	private List<User> associatedUserList;

	// bi-directional many-to-many association to UserType
	@ManyToMany(mappedBy = "associatedGroupList")
	private List<UserType> associatedUserTypeList;

	public Group() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public TextContentEntry getName() {
		return name;
	}

	public void setName(TextContentEntry name) {
		this.name = name;
	}

	public List<Permission> getAssociatedPermissions() {
		return this.associatedPermissionList;
	}

	public void setPermissions(List<Permission> permissions) {
		this.associatedPermissionList = permissions;
	}

	public List<User> getAssociatedUserList() {
		return this.associatedUserList;
	}

	public void setAssociatedUserList(List<User> userList) {
		this.associatedUserList = userList;
	}

	public List<UserType> getAssociatedUserTypeList() {
		return this.associatedUserTypeList;
	}

	public void setAssociatedUserTypeList(List<UserType> associatedUserTypeList) {
		this.associatedUserTypeList = associatedUserTypeList;
	}
}