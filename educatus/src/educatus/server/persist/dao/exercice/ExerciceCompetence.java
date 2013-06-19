package educatus.server.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "exercice.exercicecompetence")
public class ExerciceCompetence implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExerciceCompetencePK id;

	// bi-directional many-to-one association to Exercice
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "exer_id", nullable = false, insertable = false, updatable = false)
	private Exercice exercice;

	public ExerciceCompetence() {
	}

	public ExerciceCompetencePK getId() {
		return this.id;
	}

	public void setId(ExerciceCompetencePK id) {
		this.id = id;
	}

	public Exercice getExercice() {
		return this.exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}

}