package educatus.shared.persist.dao.seminary;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the seminary database table.
 * 
 */
@Entity
@Table(name="seminary.seminary")
public class Seminary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="semi_id", unique=true, nullable=false)
	private Integer semiId;

	@Column(name="dyco_content", nullable=false)
	private Integer dycoContent;

	@Column(name="semi_available", nullable=false)
	private Boolean semiAvailable;

	@Column(name="semi_datecreated", nullable=false)
	private Timestamp semiDatecreated;

	@Column(name="semi_datedeleted")
	private Timestamp semiDatedeleted;

	@Column(name="semi_datemodified", nullable=false)
	private Timestamp semiDatemodified;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="tece_title", nullable=false)
	private Integer teceTitle;

	@Column(name="user_author", nullable=false)
	private Integer userAuthor;

	@Column(name="user_lasteditor", nullable=false)
	private Integer userLasteditor;

	//bi-directional many-to-one association to Difficulty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="diff_value", nullable=false)
	private Difficulty difficulty;

	//bi-directional many-to-many association to Category
    @ManyToMany
	@JoinTable(
		name="seminarycategory"
		, joinColumns={
			@JoinColumn(name="semi_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="cate_id", nullable=false)
			}
		)
	private List<Category> categories;

	//bi-directional many-to-many association to Competence
    @ManyToMany
	@JoinTable(
		name="seminarycompetence"
		, joinColumns={
			@JoinColumn(name="semi_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="comp_id", nullable=false)
			}
		)
	private List<Competence> competences;

	//bi-directional many-to-one association to UsersSminary
	@OneToMany(mappedBy="seminary")
	private List<UsersSminary> userseminaries;

    public Seminary() {
    }

	public Integer getSemiId() {
		return this.semiId;
	}

	public void setSemiId(Integer semiId) {
		this.semiId = semiId;
	}

	public Integer getDycoContent() {
		return this.dycoContent;
	}

	public void setDycoContent(Integer dycoContent) {
		this.dycoContent = dycoContent;
	}

	public Boolean getSemiAvailable() {
		return this.semiAvailable;
	}

	public void setSemiAvailable(Boolean semiAvailable) {
		this.semiAvailable = semiAvailable;
	}

	public Timestamp getSemiDatecreated() {
		return this.semiDatecreated;
	}

	public void setSemiDatecreated(Timestamp semiDatecreated) {
		this.semiDatecreated = semiDatecreated;
	}

	public Timestamp getSemiDatedeleted() {
		return this.semiDatedeleted;
	}

	public void setSemiDatedeleted(Timestamp semiDatedeleted) {
		this.semiDatedeleted = semiDatedeleted;
	}

	public Timestamp getSemiDatemodified() {
		return this.semiDatemodified;
	}

	public void setSemiDatemodified(Timestamp semiDatemodified) {
		this.semiDatemodified = semiDatemodified;
	}

	public Integer getTeceDescription() {
		return this.teceDescription;
	}

	public void setTeceDescription(Integer teceDescription) {
		this.teceDescription = teceDescription;
	}

	public Integer getTeceTitle() {
		return this.teceTitle;
	}

	public void setTeceTitle(Integer teceTitle) {
		this.teceTitle = teceTitle;
	}

	public Integer getUserAuthor() {
		return this.userAuthor;
	}

	public void setUserAuthor(Integer userAuthor) {
		this.userAuthor = userAuthor;
	}

	public Integer getUserLasteditor() {
		return this.userLasteditor;
	}

	public void setUserLasteditor(Integer userLasteditor) {
		this.userLasteditor = userLasteditor;
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
	
}