package educatus.shared.dto.exercice;

import java.io.Serializable;

public abstract class AbstractAnswerContent implements Serializable {

	private static final long serialVersionUID = -8016661002700647940L;
	
	public abstract ExerciceQuestionType getExerciceQuestionType();

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
