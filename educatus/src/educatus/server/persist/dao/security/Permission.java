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

/**
 * The persistent class for the permission database table.
 * 
 */
@Entity
@Table(name = "security.permission")
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "PERMISSION_PERM_ID_GENERATOR", sequenceName = "security.permission_perm_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_PERM_ID_GENERATOR")
	@Column(name = "perm_id")
	private Integer id;

	@Column(name = "tece_description")
	private Integer description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name")
	private TextContentEntry textContentEntry;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "permissions")
	private List<User> users;

	// bi-directional many-to-many association to Usertype
	@ManyToMany(mappedBy = "permissions")
	private List<UserType> usertypes;

	public Permission() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDescription() {
		return this.description;
	}

	public void setDescription(Integer description) {
		this.description = description;
	}

	public TextContentEntry getTextContentEntry() {
		return this.textContentEntry;
	}

	public void setTextContentEntry(TextContentEntry textContentEntry) {
		this.textContentEntry = textContentEntry;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<UserType> getUsertypes() {
		return this.usertypes;
	}

	public void setUsertypes(List<UserType> usertypes) {
		this.usertypes = usertypes;
	}
}