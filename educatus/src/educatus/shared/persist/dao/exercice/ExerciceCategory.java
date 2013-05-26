package educatus.shared.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the exercicecategory database table.
 * 
 */
@Entity
@Table(name="exercice.exercicecategory")
public class ExerciceCategory implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ExerciceCategoryPK id;

	//bi-directional many-to-one association to Exercice
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="exer_id", nullable=false, insertable=false, updatable=false)
	private Exercice exercice;

    public ExerciceCategory() {
    }

	public ExerciceCategoryPK getId() {
		return this.id;
	}

	public void setId(ExerciceCategoryPK id) {
		this.id = id;
	}
	
	public Exercice getExercice() {
		return this.exercice;
	}

	public void setExercice(Exercice exercice) {
		this.exercice = exercice;
	}
	
}