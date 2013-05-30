package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import educatus.shared.persist.dao.dynamiccontent.DynamicContent;
import educatus.shared.persist.dao.internationalization.TextContentEntry;
import educatus.shared.persist.dao.security.User;

@Entity
@Table(name = "seminary.seminary")
public class Seminary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "semi_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_content", nullable = false, insertable = false, updatable = false)
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
	@JoinColumn(name = "tece_title", nullable = false, insertable = false, updatable = false)
	private TextContentEntry title;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = false, updatable = false)
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_author", nullable = false, insertable = false, updatable = false)
	private User author;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_lasteditor", nullable = false, insertable = false, updatable = false)
	private User lastEditor;

	// bi-directional many-to-one association to Difficulty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diff_value", nullable = false)
	private Difficulty difficulty;

	// bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(name = "seminarycategory", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "cate_id", nullable = false) })
	private List<Category> categories;

	// bi-directional many-to-many association to Competence
	@ManyToMany
	@JoinTable(name = "seminarycompetence", joinColumns = { @JoinColumn(name = "semi_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "comp_id", nullable = false) })
	private List<Competence> competences;

	// bi-directional many-to-one association to UsersSminary
	@OneToMany(mappedBy = "seminary")
	private List<UsersSminary> userseminaries;

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

	public List<UsersSminary> getUserseminaries() {
		return this.userseminaries;
	}

	public void setUserseminaries(List<UsersSminary> userseminaries) {
		this.userseminaries = userseminaries;
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