package educatus.server.businesslogic.seminarymanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Seminary;
import educatus.shared.dto.pagecontent.SeminaryHomePageListingContent;
import educatus.shared.dto.seminary.CategoryCoreContent;
import educatus.shared.dto.seminary.SeminaryCoreContent;

@Singleton
public class SeminaryHomeListingBuilder {
	
	@Inject
	private SeminaryDao semDao;
	
	public SeminaryHomePageListingContent buildSeminaryHomeListingContent(int parentId, String culture, String language) {
		
		SeminaryHomePageListingContent pageContent = new SeminaryHomePageListingContent();
		
		try {
			
			Category parentCategory = semDao.findCategoryById(parentId);
			
			// We set common parent 
			CategoryCoreContent commonParent = new CategoryCoreContent();
			commonParent.setId(parentCategory.getId());
			commonParent.setName(InternationalizationUtility.getTranslationEntry(parentCategory.getName(), culture, language).getTcteTranslation());
			commonParent.setDescription(InternationalizationUtility.getTranslationEntry(parentCategory.getDescription(), culture, language).getTcteTranslation());
			pageContent.setCommonParent(commonParent);
			
			// TODO get actual childrens
			
			List<Seminary> seminaryList = semDao.findAllSeminary();
			
			for (Seminary seminary : seminaryList) {
				
				// Copy-paste from SeminaryContentFactory, we need to remove duplicate code, create a utility parser ?
				SeminaryCoreContent coreContent = new SeminaryCoreContent();
				coreContent.setId(seminary.getSemiId());
				coreContent.setTitle(InternationalizationUtility.getTranslationEntry(seminary.getTitle(), culture, language).getTcteTranslation());
				coreContent.setDescription(InternationalizationUtility.getTranslationEntry(seminary.getDescription(), culture, language).getTcteTranslation());		
				coreContent.setDifficulty(InternationalizationUtility.getTranslationEntry(seminary.getDifficulty().getName(), culture, language).getTcteTranslation());
				coreContent.setAuthor(seminary.getAuthor().getFirstName() + " " + seminary.getAuthor().getLastName());
				coreContent.setLastEditor(seminary.getLastEditor().getFirstName() + " " + seminary.getLastEditor().getLastName());
				coreContent.setCreatedDate(seminary.getDateCreated().toString());
				coreContent.setEditedDate(seminary.getDateModified().toString());
				
				pageContent.getSeminariesChildren().add(coreContent);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}		
		
		return pageContent;
	}	
}
