package educatus.server.persist.dao.seminary;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;

@Entity
@NamedQueries({
	@NamedQuery(name = Seminary.FIND_ALL, query = "SELECT s FROM Seminary s")})
@Table(name = "seminary.seminary")
public class Seminary implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "SEMINARY.FIND_ALL";
	
	@Id
	@SequenceGenerator(name = "seminary_semi_id", sequenceName = "seminary.seminary_semi_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seminary_semi_id")
	@Column(name = "semi_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_content", nullable = false, insertable = true, updatable = true)
	private DynamicContent dynamicContent;

	@Column(name = "semi_available", nullable = false)
	private Boolean isAvailable;

	@Column(name = "semi_datecreated", nullable = false)
	private Timestamp dateCreated;

	@Column(name = "semi_datedeleted")
	private Timestamp dateDeleted;

	@Column(name = "semi_datemodified", nullable = false)
	private Timestamp dateModified;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_title", nullable = false, insertable = true, updatable = true)
	private TextContentEntry title;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = true, updatable = true)
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_author", nullable = false, insertable = true, updatable = true)
	private User author;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_lasteditor", nullable = false, insertable = true, updatable = true)
	private User lastEditor;

	// bi-directional many-to-one association to Difficulty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diff_value", nullable = false, insertable = true, updatable = true)
	private Difficulty difficulty;

	// bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(name = "seminarycategory", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "cate_id", nullable = false) })
	private List<Category> categories;

	// bi-directional many-to-many association to Competence
	@ManyToMany
	@JoinTable(name = "seminarycompetence", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "comp_id", nullable = false) })
	private List<Competence> competences;

	// bi-directional many-to-one association to UsersSeminary
	@ManyToMany
	@JoinTable(name = "userseminary", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
	private List<User> completedSeminaryUsers;

	// bi-directional many-to-one association to UsersSeminary
	@ManyToMany
	@JoinTable(name = "seminaryassociateduser", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false) })
	private List<User> seminaryEditUser;
	
	public List<User> getSeminaryEditUser() {
		return seminaryEditUser;
	}

	public void setSeminaryEditUser(List<User> seminaryEditUser) {
		this.seminaryEditUser = seminaryEditUser;
	}

	public Seminary() {
	}

	public Integer getSemiId() {
		return this.id;
	}

	public void setSemiId(Integer semiId) {
		this.id = semiId;
	}

	public Boolean isAvailable() {
		return this.isAvailable;
	}

	public void setAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateDeleted() {
		return this.dateDeleted;
	}

	public void setDateDeleted(Timestamp dateDeleted) {
		this.dateDeleted = dateDeleted;
	}

	public Timestamp getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(Timestamp dateModified) {
		this.dateModified = dateModified;
	}

	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public List<Category> getCategories() {
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Competence> getCompetences() {
		return this.competences;
	}

	public void setCompetences(List<Competence> competences) {
		this.competences = competences;
	}

	public List<User> getCompletedSeminaryUsers() {
		return this.completedSeminaryUsers;
	}

	public void setCompletedSeminaryUsers(List<User> userseminaries) {
		this.completedSeminaryUsers = userseminaries;
	}

	public DynamicContent getDynamicContent() {
		return dynamicContent;
	}

	public void setDynamicContent(DynamicContent dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public TextContentEntry getTitle() {
		return title;
	}

	public void setTitle(TextContentEntry title) {
		this.title = title;
	}

	public TextContentEntry getDescription() {
		return description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public User getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(User lastEditor) {
		this.lastEditor = lastEditor;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}