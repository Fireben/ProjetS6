package educatus.shared.dto.exercice;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciceContent implements Serializable {

	private static final long serialVersionUID = 12464514921804686L;

	private ExerciceCoreContent coreContent;
	private List<ExerciceQuestionContent> questionList = new ArrayList<ExerciceQuestionContent>();
	private List<Integer> categories = new ArrayList<Integer>();
	
	public ExerciceCoreContent getCoreContent() {
		return coreContent;
	}

	public void setCoreContent(ExerciceCoreContent coreContent) {
		this.coreContent = coreContent;
	}

	public List<ExerciceQuestionContent> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<ExerciceQuestionContent> questionList) {
		this.questionList = questionList;
	}

	public List<Integer> getCategories() {
		return categories;
	}

	public void setCategories(List<Integer> categories) {
		this.categories = categories;
	}
}
