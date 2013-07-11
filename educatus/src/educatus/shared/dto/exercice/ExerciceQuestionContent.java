package educatus.shared.dto.exercice;

import java.util.ArrayList;
import java.util.List;

import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;

public class ExerciceQuestionContent {

	private int id;
	private int sequence;
	private int score;
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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
