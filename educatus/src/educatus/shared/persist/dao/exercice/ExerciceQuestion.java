package educatus.shared.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the exercicequestion database table.
 * 
 */
@Entity
@Table(name="exercice.exercicequestion")
public class ExerciceQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="exqu_id", unique=true, nullable=false)
	private Integer exquId;

	@Column(name="dyco_content", nullable=false)
	private Integer dycoContent;

	@Column(name="exqu_score", nullable=false)
	private Integer exquScore;

	@Column(name="exqu_sequence", nullable=false)
	private Integer exquSequence;

	//bi-directional many-to-one association to Exercice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="exer_id", nullable=false)
	private Exercice exercice;

	//bi-directional many-to-one association to ExerciceQuestionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="exqt_type", unique=true, nullable=false)
	private ExerciceQuestionType exercicequestiontype;

    public ExerciceQuestion() {
    }

	public Integer getExquId() {
		return this.exquId;
	}

	public void setExquId(Integer exquId) {
		this.exquId = exquId;
	}

	public Integer getDycoContent() {
		return this.dycoContent;
	}

	public void setDycoContent(Integer dycoContent) {
		this.dycoContent = dycoContent;
	}

	public Integer getExquScore() {
		return this.exquScore;
	}

	public void setExquScore(Integer exquScore) {
		this.exquScore = exquScore;
	}

	public Integer getExquSequence() {
		return this.exquSequence;
	}

	public void setExquSequence(Integer exquSequence) {
		this.exquSequence = exquSequence;
	}
	
	public Exercice getExercice() {
		return this.exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	
	public ExerciceQuestionType getExercicequestiontype() {
		return this.exercicequestiontype;
	}

	public void setExercicequestiontype(ExerciceQuestionType exercicequestiontype) {
		this.exercicequestiontype = exercicequestiontype;
	}
}