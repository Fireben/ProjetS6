package educatus.shared.dto.exercice;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;

public class ExerciceQuestion {

	private int id;
	private List<AbstractDynamicSection> questionContext = new ArrayList<AbstractDynamicSection>();
	private ExerciceQuestionType questionType;
	private AbstractAnswerContent answer;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<AbstractDynamicSection> getQuestionContext() {
		return questionContext;
	}

	public void setQuestionContext(List<AbstractDynamicSection> questionContext) {
		this.questionContext = questionContext;
	}

	public AbstractAnswerContent getAnswer() {
		return answer;
	}

	public void setAnswer(AbstractAnswerContent answer) {
		this.answer = answer;
	}

	public ExerciceQuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(ExerciceQuestionType questionType) {
		this.questionType = questionType;
	}
}
