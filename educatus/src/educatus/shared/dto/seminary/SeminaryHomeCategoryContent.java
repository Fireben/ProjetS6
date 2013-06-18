package educatus.shared.dto.seminary;

import java.io.Serializable;
import java.util.ArrayList;

public class SeminaryHomeCategoryContent implements Serializable {

	private static final long serialVersionUID = 6160737635292640612L;

	private CategoryCoreContent commonParent;
	private ArrayList<CategoryCoreContent> categoryChildren = new ArrayList<CategoryCoreContent>();

	public CategoryCoreContent getCommonParent() {
		return commonParent;
	}

	public void setCommonParent(CategoryCoreContent commonParent) {
		this.commonParent = commonParent;
	}

	public ArrayList<CategoryCoreContent> getCategoryChildren() {
		return categoryChildren;
	}

	public void setCategoryChildren(ArrayList<CategoryCoreContent> categoryChildren) {
		this.categoryChildren = categoryChildren;
	}
}
