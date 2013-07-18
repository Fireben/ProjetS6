package educatus.shared.dto.user;

import java.io.Serializable;
import java.util.List;

import educatus.shared.dto.seminary.CategoryCoreContent;

public class UserStatisticsContent implements Serializable {

	public static class CategoryStat implements Serializable {
		
		private static final long serialVersionUID = 319026379618336256L;
		
		private CategoryCoreContent categoryCoreContent;
		private int totalSeminaries;
		private int completedSeminaries;

		public CategoryCoreContent getCategoryCoreContent() {
			return categoryCoreContent;
		}

		public void setCategoryCoreContent(CategoryCoreContent categoryCoreContent) {
			this.categoryCoreContent = categoryCoreContent;
		}

		public int getTotalSeminaries() {
			return totalSeminaries;
		}

		public void setTotalSeminaries(int totalSeminaries) {
			this.totalSeminaries = totalSeminaries;
		}

		public int getCompletedSeminaries() {
			return completedSeminaries;
		}

		public void setCompletedSeminaries(int completedSeminaries) {
			this.completedSeminaries = completedSeminaries;
		}
	}

	private static final long serialVersionUID = -2381060687631033890L;

	private List<CategoryStat> categoryStatList;

	public List<CategoryStat> getCategoryStatList() {
		return categoryStatList;
	}

	public void setCategoryStatList(List<CategoryStat> categoryStatList) {
		this.categoryStatList = categoryStatList;
	}
}
