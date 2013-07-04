package educatus.server.businesslogic.seminarymanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.SeminaryAdapter;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.seminary.Category;
import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.dto.seminary.CategoryCoreContent;

@Singleton
public class SeminaryHomeCategoryBuilder {

	@Inject
	private SeminaryDao semDao;

	public SeminaryHomePageCategoryContent buildSeminaryHomeCategoryContent(Integer parentId, String culture, String language) {

		SeminaryHomePageCategoryContent pageContent = new SeminaryHomePageCategoryContent();

		try {
			
			// Top level category
			if (parentId == null) {
				// Top level category parent is always null
				pageContent.setCommonParent(null);
				
				// we get all children
				List<Category> categoryList = semDao.findTopLevelCategories();
				
				for (Category category : categoryList) {					
					CategoryCoreContent content = SeminaryAdapter.categoryToCategoryCoreContent(category, culture, language);
					pageContent.getCategoryChildren().add(content);
				}			
				
			} else {
				
				Category parentCategory  = semDao.findCategoryById(parentId);
				List<Category> childrenCategories = semDao.findChildrenCategories(parentCategory.getId());
				
				// We set common parent 
				CategoryCoreContent commonParent = SeminaryAdapter.categoryToCategoryCoreContent(parentCategory, culture, language);
				pageContent.setCommonParent(commonParent);
				
				for (Category category : childrenCategories) {
					
					CategoryCoreContent categoryCoreContent = SeminaryAdapter.categoryToCategoryCoreContent(category, culture, language);
					// We add category content to the list
					pageContent.getCategoryChildren().add(categoryCoreContent);
				}	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return pageContent;
	}
}
