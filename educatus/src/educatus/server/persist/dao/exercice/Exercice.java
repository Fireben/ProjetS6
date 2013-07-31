package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Competence;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.server.persist.dao.seminary.Seminary;

@Entity
@NamedQueries({
	@NamedQuery(name = Exercice.FIND_ALL, query = "SELECT e FROM Exercice e"),
	@NamedQuery(name = Exercice.FIND_BY_CATEGORY, query = "Select distinct e FROM Exercice e join e.categories c where c.id in :" + Exercice.FIND_BY_CATEGORY_PARAM),
	@NamedQuery(name = Exercice.FIND_BY_IDS, query = "Select distinct e FROM Exercice e where e.id in :" + Exercice.FIND_BY_IDS_PARAM)})
@Table(name = "exercice.exercice")
public class Exercice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "Exercice.FIND_ALL";
	public static final String FIND_BY_CATEGORY = "Exercice.FINB_BY_CATEGORY";
	public static final String FIND_BY_CATEGORY_PARAM = "catIds";
	public static final String FIND_BY_IDS = "EXERCICE.FIND_BY_IDS";
	public static final String FIND_BY_IDS_PARAM = "ids";
	
	@Id
	@SequenceGenerator(name = "exercice_exer_id", sequenceName = "exercice.exercice_exer_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercice_exer_id")
	@Column(name = "exer_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to Difficulty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "diff_value", nullable = false, insertable = true, updatable = true)
	private Difficulty difficulty;

	@Column(name = "exer_available", nullable = false)
	private Boolean isAvailable;

	@Column(name = "exer_datecreated", nullable = false)
	private Timestamp dateCreated;

	@Column(name = "exer_datedeleted")
	private Timestamp dateDeleted;

	@Column(name = "exer_datemodified", nullable = false)
	private Timestamp dateModified;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description", nullable = false, insertable = true, updatable = true)
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_title", nullable = false, insertable = true, updatable = true)
	private TextContentEntry title;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_author", nullable = false, insertable = true, updatable = true)
	private User author;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_lasteditor", nullable = false, insertable = true, updatable = true)
	private User editor;

	// bi-directional many-to-many association to Category
	@ManyToMany
	@JoinTable(name = "exercice.exercicecategory", joinColumns = { @JoinColumn(name = "exer_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "cate_id", nullable = false) })
	private List<Category> categories = new ArrayList<Category>();

	// bi-directional many-to-many association to Competence
	@ManyToMany
	@JoinTable(name = "exercice.exercicecompetence", joinColumns = { @JoinColumn(name = "exer_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "comp_id", nullable = false) })
	private List<Competence> competences = new ArrayList<Competence>();
	
	// bi-directional many-to-one association to ExerciceQuestion
	@OneToMany(mappedBy = "exercice")
	private List<ExerciceQuestion> exercicequestions;

	public Exercice() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Difficulty getDifficultyValue() {
		return this.difficulty;
	}

	public void setDifficultyValue(Difficulty difficulty) {
		this.difficulty = difficulty;
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

	public TextContentEntry getDescription() {
		return this.description;
	}

	public void setDescription(TextContentEntry description) {
		this.description = description;
	}

	public TextContentEntry getTitle() {
		return this.title;
	}

	public void setTitle(TextContentEntry title) {
		this.title = title;
	}

	public User getAuthor() {
		return this.author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getLastEditor() {
		return this.editor;
	}

	public void setLastEditor(User editor) {
		this.editor = editor;
	}

	public List<Category> getCategoryList() {
		return this.categories;
	}

	public void setCategoryList(List<Category> categories) {
		this.categories = categories;
	}

	public List<Competence> getCompetenceList() {
		return this.competences;
	}

	public void setCompetenceList(List<Competence> competences) {
		this.competences = competences;
	}

	public List<ExerciceQuestion> getExercicequestions() {
		return this.exercicequestions;
	}

	public void setExercicequestions(List<ExerciceQuestion> exercicequestions) {
		this.exercicequestions = exercicequestions;
	}

}