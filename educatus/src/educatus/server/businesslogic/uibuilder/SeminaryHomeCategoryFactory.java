package educatus.server.businesslogic.uibuilder;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.internationalization.ImageExternal;
import educatus.server.persist.dao.seminary.Category;
import educatus.shared.dto.seminary.SeminaryHomeCategoryContent;
import educatus.shared.dto.seminary.SeminaryHomeCategoryContent.CategoryContent;

@Singleton
public class SeminaryHomeCategoryFactory {

	@Inject
	private SeminaryDao semDao;

	public SeminaryHomeCategoryContent createSeminaryHomeCategoryContent(
			Integer parentId, String culture, String language) {

		SeminaryHomeCategoryContent pageContent = new SeminaryHomeCategoryContent();

		try {
			
			// Top level category
			if (parentId == null) {
				// Top level category parent is always null
				pageContent.setCommonParent(null);
				
				// we get all children
				List<Category> categoryList = semDao.findTopLevelCategories();
				
				for (Category category : categoryList) {
					
					CategoryContent content = new CategoryContent();
					// TODO We need to get the correct translation in the entries
					content.setId(category.getId());
					content.setName(category.getName().getTextContentTranslationEntries().get(0).getTcteTranslation());
					content.setDescription(category.getDescription().getTextContentTranslationEntries().get(0).getTcteTranslation());
					// We know it's an externalImage
					ImageExternal externalImage = (ImageExternal) category.getImage();
					content.setImageUrl(externalImage.getUrl());
					
					// We add category content to the list
					pageContent.getCategoryChildren().add(content);
				}			
				
			} else {
				
				Category parentCategory = semDao.findCategoryById(parentId);
				List<Category> childrenCategories = semDao.findChildrenCategories(parentCategory.getId());
				
				// We set common parent 
				CategoryContent commonParent = new CategoryContent();
				commonParent.setId(parentCategory.getId());
				commonParent.setName(parentCategory.getName().getTextContentTranslationEntries().get(0).getTcteTranslation());
				commonParent.setDescription(parentCategory.getDescription().getTextContentTranslationEntries().get(0).getTcteTranslation());
				// We know it's an externalImage
				ImageExternal commonParentExternalImage = (ImageExternal) parentCategory.getImage();
				commonParent.setImageUrl(commonParentExternalImage.getUrl());
				pageContent.setCommonParent(commonParent);
				
				for (Category category : childrenCategories) {
					
					CategoryContent content = new CategoryContent();
					// TODO We need to get the correct translation in the entries
					content.setId(category.getId());
					content.setName(category.getName().getTextContentTranslationEntries().get(0).getTcteTranslation());
					content.setDescription(category.getDescription().getTextContentTranslationEntries().get(0).getTcteTranslation());
					// We know it's an externalImage
					ImageExternal externalImage = (ImageExternal) category.getImage();
					content.setImageUrl(externalImage.getUrl());
					
					// We add category content to the list
					pageContent.getCategoryChildren().add(content);
				}	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}		

		return pageContent;
	}
}
