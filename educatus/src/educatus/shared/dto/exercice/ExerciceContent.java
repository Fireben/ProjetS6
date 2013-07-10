package educatus.shared.dto.exercice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciceContent implements Serializable {

	private static final long serialVersionUID = 12464514921804686L;

	private ExerciceCoreContent coreContent;
	private List<ExerciceQuestion> questionList = new ArrayList<ExerciceQuestion>();
	
	public ExerciceCoreContent getCoreContent() {
		return coreContent;
	}

	public void setCoreContent(ExerciceCoreContent coreContent) {
		this.coreContent = coreContent;
	}

	public List<ExerciceQuestion> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<ExerciceQuestion> questionList) {
		this.questionList = questionList;
	}
}
