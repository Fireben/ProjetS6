package educatus.shared.dto.pagecontent;

import java.io.Serializable;
import java.util.ArrayList;

import educatus.shared.dto.seminary.CategoryCoreContent;

public class SeminaryHomePageCategoryContent implements Serializable {

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
