package educatus.shared.businesslogic.dto.seminary;

import java.io.Serializable;
import java.util.ArrayList;

public class SeminaryHomeCategoryContent implements Serializable {

	public static class CategoryContent {
		private int id;
		private String name;
		private String description;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	private static final long serialVersionUID = 6160737635292640612L;

	private CategoryContent commonParent;
	private ArrayList<CategoryContent> categoryChildren = new ArrayList<CategoryContent>();

	public CategoryContent getCommonParent() {
		return commonParent;
	}

	public void setCommonParent(CategoryContent commonParent) {
		this.commonParent = commonParent;
	}

	public ArrayList<CategoryContent> getCategoryChildren() {
		return categoryChildren;
	}

	public void setCategoryChildren(ArrayList<CategoryContent> categoryChildren) {
		this.categoryChildren = categoryChildren;
	}

}
