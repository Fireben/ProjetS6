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
@Table(name = "security.permission")
public class Permission implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERMISSION_PERM_ID_GENERATOR", sequenceName = "security.permission_perm_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_PERM_ID_GENERATOR")
	@Column(name = "perm_id")
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description")
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name")
	private TextContentEntry name;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "associatedPermissionList")
	private List<User> associatedUserList;

	// bi-directional many-to-many association to Usertype
	@ManyToMany(mappedBy = "associatedPermissionList")
	private List<UserType> associatedUserTypeList;

	@ManyToMany
	@JoinTable(name = "grouppermission", joinColumns = { @JoinColumn(name = "perm_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "grou_id", nullable = false) })
	private List<Group> associatedGroupList;

	public Permission() {
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

	public List<User> getAssociatedUserList() {
		return this.associatedUserList;
	}

	public void setAssociatedUserList(List<User> associatedUserList) {
		this.associatedUserList = associatedUserList;
	}

	public List<UserType> getAssociatedUserTypeList() {
		return this.associatedUserTypeList;
	}

	public void setAssociatedUserTypeList(List<UserType> associatedUserTypeList) {
		this.associatedUserTypeList = associatedUserTypeList;
	}
	
	public List<Group> getAssociatedGroupList() {
		return associatedGroupList;
	}

	public void setAssociatedGroupList(List<Group> associatedGroupList) {
		this.associatedGroupList = associatedGroupList;
	}
}