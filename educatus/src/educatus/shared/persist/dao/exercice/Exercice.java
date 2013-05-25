package educatus.shared.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the exercice database table.
 * 
 */
@Entity
@Table(name="exercice.exercice")
public class Exercice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exer_id", unique=true, nullable=false)
	private Integer exerId;

	@Column(name="diff_value", nullable=false)
	private Integer diffValue;

	@Column(name="exer_available", nullable=false)
	private Boolean exerAvailable;

	@Column(name="exer_datecreated", nullable=false)
	private Timestamp exerDatecreated;

	@Column(name="exer_datedeleted")
	private Timestamp exerDatedeleted;

	@Column(name="exer_datemodified", nullable=false)
	private Timestamp exerDatemodified;

	@Column(name="tece_description", nullable=false)
	private Integer teceDescription;

	@Column(name="tece_title", nullable=false)
	private Integer teceTitle;

	@Column(name="user_author", nullable=false)
	private Integer userAuthor;

	@Column(name="user_lasteditor", nullable=false)
	private Integer userLasteditor;

	//bi-directional many-to-one association to ExerciceCategory
	@OneToMany(mappedBy="exercice")
	private List<ExerciceCategory> exercicecategories;

	//bi-directional many-to-one association to ExerciceCompetence
	@OneToMany(mappedBy="exercice")
	private List<ExerciceCompetence> exercicecompetences;

	//bi-directional many-to-one association to ExerciceQuestion
	@OneToMany(mappedBy="exercice")
	private List<ExerciceQuestion> exercicequestions;

    public Exercice() {
    }

	public Integer getExerId() {
		return this.exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public Integer getDiffValue() {
		return this.diffValue;
	}

	public void setDiffValue(Integer diffValue) {
		this.diffValue = diffValue;
	}

	public Boolean getExerAvailable() {
		return this.exerAvailable;
	}

	public void setExerAvailable(Boolean exerAvailable) {
		this.exerAvailable = exerAvailable;
	}

	public Timestamp getExerDatecreated() {
		return this.exerDatecreated;
	}

	public void setExerDatecreated(Timestamp exerDatecreated) {
		this.exerDatecreated = exerDatecreated;
	}

	public Timestamp getExerDatedeleted() {
		return this.exerDatedeleted;
	}

	public void setExerDatedeleted(Timestamp exerDatedeleted) {
		this.exerDatedeleted = exerDatedeleted;
	}

	public Timestamp getExerDatemodified() {
		return this.exerDatemodified;
	}

	public void setExerDatemodified(Timestamp exerDatemodified) {
		this.exerDatemodified = exerDatemodified;
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

	public List<ExerciceCategory> getExercicecategories() {
		return this.exercicecategories;
	}

	public void setExercicecategories(List<ExerciceCategory> exercicecategories) {
		this.exercicecategories = exercicecategories;
	}
	
	public List<ExerciceCompetence> getExercicecompetences() {
		return this.exercicecompetences;
	}

	public void setExercicecompetences(List<ExerciceCompetence> exercicecompetences) {
		this.exercicecompetences = exercicecompetences;
	}
	
	public List<ExerciceQuestion> getExercicequestions() {
		return this.exercicequestions;
	}

	public void setExercicequestions(List<ExerciceQuestion> exercicequestions) {
		this.exercicequestions = exercicequestions;
	}
	
}