package educatus.server.businesslogic.seminarymanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.SeminaryAdapter;
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
			CategoryCoreContent commonParent = SeminaryAdapter.categoryToCategoryCoreContent(parentCategory, culture, language);
			pageContent.setCommonParent(commonParent);
			
			// TODO get actual childrens			
			List<Seminary> seminaryList = semDao.findAllSeminary();
			
			for (Seminary seminary : seminaryList) {
				SeminaryCoreContent seminaryCoreContent = SeminaryAdapter.seminaryToSeminaryCoreContent(seminary, culture, language);				
				pageContent.getSeminariesChildren().add(seminaryCoreContent);
			}
						
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		
		return pageContent;
	}	
}
