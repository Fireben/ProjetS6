package educatus.server.persist.dao;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.shared.persist.dao.internationalization.TextContentEntry;
import educatus.shared.persist.dao.seminary.Category;

@Singleton
public class SeminaryDao {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject 
	private InternationalizationDao internationalizationDao;
	
	public Category createNewCategory(int nameTeceId, int descriptionTeceId, Integer parent) throws Exception {
		
		internationalizationDao.findAllTextContentTranslationEntry();

		entityManager.getTransaction().begin();
		TextContentEntry nameTece = entityManager.find(TextContentEntry.class, nameTeceId);
		TextContentEntry descriptionTece = entityManager.find(TextContentEntry.class, descriptionTeceId);
		
		Category category = new Category();
		category.setDeleteflag(false);
		category.setName(nameTece);
		category.setDescription(descriptionTece);
		
		// Insert Object
		entityManager.persist(category);		
		// TODO check for rollback or throw exception higher ?
		entityManager.getTransaction().commit();
				
		return null;
	}	
}
