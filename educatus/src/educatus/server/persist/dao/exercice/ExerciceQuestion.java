package educatus.server.persist.dao.exercice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;

@Entity
@Table(name = "exercice.exercicequestion")
public class ExerciceQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "exercicequestion_exqu_id", sequenceName = "exercice.exercicequestion_exqu_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "exercicequestion_exqu_id")
	@Column(name = "exqu_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to DynamicContent
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dyco_content", nullable = false, insertable = true, updatable = true)
	private DynamicContent dynamicContent;

	@Column(name = "exqu_score", nullable = false)
	private Integer score;

	@Column(name = "exqu_sequence", nullable = false)
	private Integer sequence;

	// bi-directional many-to-one association to Exercice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exer_id", nullable = false)
	private Exercice exercice;

	// bi-directional many-to-one association to ExerciceQuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exqt_type", unique = true, nullable = false)
	private ExerciceQuestionType exercicequestiontype;

	public ExerciceQuestion() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DynamicContent getDynamicContent() {
		return this.dynamicContent;
	}

	public void setDynamicContent(DynamicContent dynamicContent) {
		this.dynamicContent = dynamicContent;
	}

	public Integer getExquScore() {
		return this.score;
	}

	public void setExquScore(Integer exquScore) {
		this.score = exquScore;
	}

	public Integer getExquSequence() {
		return this.sequence;
	}

	public void setExquSequence(Integer exquSequence) {
		this.sequence = exquSequence;
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

	public void setExercicequestiontype(
			ExerciceQuestionType exercicequestiontype) {
		this.exercicequestiontype = exercicequestiontype;
	}
}