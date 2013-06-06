package educatus.server.persist.dao.achievement;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;


/**
 * The persistent class for the achievement database table.
 * 
 */
@Entity
@Table(name="achievement.achievement")
public class Achievement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "ACHIEVEMENT_ACHI_ID_GENERATOR", sequenceName = "achievement.achievement_achi_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACHIEVEMENT_ACHI_ID_GENERATOR")
	@Column(name="achi_id", unique=true, nullable=false)
	private Integer id;

	@Column(name="achi_datecreated", nullable=false)
	private Timestamp dateCreated;

	@Column(name="achi_datemodified", nullable=false)
	private Timestamp dateModified;

	@Column(name="achi_enable", nullable=false)
	private Boolean enabled;
	
	// bi-directional many-to-one association to TextContentEntry
	// A Seminary contains a reference to a TextContentEntry for his description
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_description")
	private TextContentEntry description;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tece_name")
	private TextContentEntry name;
	
	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_author")
	private User author;

	// bi-directional many-to-one association to TextContentEntry
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_lasteditor")
	private User lastEditor;
	
	//bi-directional many-to-one association to AchievementExercice
	@OneToMany(mappedBy="achievement")
	private List<AchievementExercice> achievementexercices;

	//bi-directional many-to-many association to Achievement
    @ManyToMany
	@JoinTable(
		name="achievementlist"
		, joinColumns={
			@JoinColumn(name="achi_child", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="achi_parent", nullable=false)
			}
		)
	private List<Achievement> achievements1;

	//bi-directional many-to-many association to Achievement
	@ManyToMany(mappedBy="achievements1")
	private List<Achievement> achievements2;

	//bi-directional many-to-one association to AchievementSeminary
	@OneToMany(mappedBy="achievement")
	private List<AchievementSeminary> achievementseminaries;

    public Achievement() {
    }

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAchiDatecreated() {
		return this.dateCreated;
	}

	public void setAchiDatecreated(Timestamp achiDatecreated) {
		this.dateCreated = achiDatecreated;
	}

	public Timestamp getAchiDatemodified() {
		return this.dateModified;
	}

	public void setAchiDatemodified(Timestamp achiDatemodified) {
		this.dateModified = achiDatemodified;
	}

	public Boolean getAchiEnable() {
		return this.enabled;
	}

	public void setAchiEnable(Boolean achiEnable) {
		this.enabled = achiEnable;
	}

	public List<AchievementExercice> getAchievementexercices() {
		return this.achievementexercices;
	}

	public void setAchievementexercices(List<AchievementExercice> achievementexercices) {
		this.achievementexercices = achievementexercices;
	}
	
	public List<Achievement> getAchievements1() {
		return this.achievements1;
	}

	public void setAchievements1(List<Achievement> achievements1) {
		this.achievements1 = achievements1;
	}
	
	public List<Achievement> getAchievements2() {
		return this.achievements2;
	}

	public void setAchievements2(List<Achievement> achievements2) {
		this.achievements2 = achievements2;
	}
	
	public List<AchievementSeminary> getAchievementseminaries() {
		return this.achievementseminaries;
	}

	public void setAchievementseminaries(List<AchievementSeminary> achievementseminaries) {
		this.achievementseminaries = achievementseminaries;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public User getLastEditor() {
		return lastEditor;
	}

	public void setLastEditor(User lastEditor) {
		this.lastEditor = lastEditor;
	}
	
}