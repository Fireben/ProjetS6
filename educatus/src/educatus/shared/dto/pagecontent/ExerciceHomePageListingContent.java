package educatus.shared.dto.pagecontent;

import java.io.Serializable;
import java.util.ArrayList;

import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.seminary.CategoryCoreContent;

public class ExerciceHomePageListingContent implements Serializable {

	private static final long serialVersionUID = 6160737635292640612L;

	private CategoryCoreContent commonParent = null;
	private ArrayList<ExerciceCoreContent> exercicesChildren = new ArrayList<ExerciceCoreContent>();

	public CategoryCoreContent getCommonParent() {
		return commonParent;
	}

	public void setCommonParent(CategoryCoreContent commonParent) {
		this.commonParent = commonParent;
	}

	public ArrayList<ExerciceCoreContent> getExercicesChildren() {
		return exercicesChildren;
	}

	public void setExercicesChildren(ArrayList<ExerciceCoreContent> exercicesChildren) {
		this.exercicesChildren = exercicesChildren;
	}
}
