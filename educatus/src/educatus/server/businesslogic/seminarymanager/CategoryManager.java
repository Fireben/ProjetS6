package educatus.server.businesslogic.seminarymanager;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.seminary.Category;
import educatus.shared.dto.seminary.CategoryCoreContent;

@Singleton
public class CategoryManager {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";

	@Inject
	SeminaryDao seminaryDao;
		
	public List<CategoryCoreContent> getAllCategory() throws Exception{
		
		List<CategoryCoreContent> categoryCoreContentList = new ArrayList<CategoryCoreContent>();
		
		List<Category> categoryList = seminaryDao.findAllCategories();
		
		for (Category category : categoryList) {
			CategoryCoreContent categoryCoreContent = SeminaryAdapter.categoryToCategoryCoreContent(category, CA_CULT, EN_LANG);
			categoryCoreContentList.add(categoryCoreContent);
		}
		
		return categoryCoreContentList;
	}
}
