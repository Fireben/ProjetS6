package educatus.shared.persist.dao.exercice;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the userexercicequestion database table.
 * 
 */
@Entity
@Table(name="exercice.userexercicequestion")
public class UserExerciceQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserExerciceQuestionPK id;

    public UserExerciceQuestion() {
    }

	public UserExerciceQuestionPK getId() {
		return this.id;
	}

	public void setId(UserExerciceQuestionPK id) {
		this.id = id;
	}	
}