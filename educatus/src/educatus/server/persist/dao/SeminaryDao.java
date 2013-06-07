package educatus.server.persist.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.seminary.Category;

@Singleton
public class SeminaryDao {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject 
	private InternationalizationDao internationalizationDao;
	
	public Category createNewCategory(int nameTeceId, int descriptionTeceId, int imageId, Integer parent) throws Exception {
		
		internationalizationDao.findAllTextContentTranslationEntry();

		entityManager.getTransaction().begin();
		TextContentEntry nameTece = entityManager.find(TextContentEntry.class, nameTeceId);
		TextContentEntry descriptionTece = entityManager.find(TextContentEntry.class, descriptionTeceId);		
		Image image = entityManager.find(Image.class, imageId);
		Category parentCategory = null;
		
		if (parent != null){
			parentCategory = entityManager.find(Category.class, parent);
		}
		
		Category category = new Category();
		category.setDeleteflag(false);
		category.setName(nameTece);
		category.setDescription(descriptionTece);
		category.setImage(image);
		category.setParentCategory(parentCategory);
		
		// Insert Object
		entityManager.persist(category);		
		// TODO check for rollback or throw exception higher ?
		entityManager.getTransaction().commit();
				
		return category;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Category> findChildrensCategories(int parentId){
		
		Category parentCategory = entityManager.find(Category.class, parentId);
		List<?> resultList = entityManager.createNamedQuery(
				Category.FIND_ALL_CHILDRENS).setParameter(Category.FIND_ALL_CHILDRENS_PARAM_NAME, parentCategory).getResultList();

		return (List<Category>) resultList;
	}
}
