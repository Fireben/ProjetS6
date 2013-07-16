package educatus.server.persist.dao.exercice;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "exqt_type", discriminatorType = DiscriminatorType.INTEGER)
@Table(name = "exercice.vanwser")
public abstract class Answer implements Serializable {

	private static final long serialVersionUID = -782926302852300985L;

	@Id
	@Column(name = "exqu_id", unique = true, nullable = false)
	private Integer id;

	// bi-directional many-to-one association to ExerciceQuestionType
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exqt_type", nullable = false, insertable = false, updatable = false)
	private ExerciceQuestionType exerciceQuestionType;
	
	@OneToOne
	@JoinColumns({
		@JoinColumn(name = "exqu_id", nullable = false, insertable = false, updatable = false ),
	    @JoinColumn(name = "exqt_type", nullable = false, insertable = false, updatable = false )
	})	
	private ExerciceQuestion exerciceQuestion;

	public Answer(){		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExerciceQuestionType getExerciceQuestionType() {
		return exerciceQuestionType;
	}

	public void setExerciceQuestionType(ExerciceQuestionType exercicequestiontype) {
		this.exerciceQuestionType = exercicequestiontype;
	}

	public ExerciceQuestion getExerciceQuestion() {
		return exerciceQuestion;
	}

	public void setExerciceQuestion(ExerciceQuestion exerciceQuestion) {
		this.exerciceQuestion = exerciceQuestion;
	}
}
