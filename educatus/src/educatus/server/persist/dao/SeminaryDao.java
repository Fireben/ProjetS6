package educatus.server.persist.dao;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.server.persist.dao.seminary.Seminary;

@Singleton
public class SeminaryDao {
	
	@Inject
	private EntityManager entityManager;
	
	@Inject 
	private InternationalizationDao internationalizationDao;
	
	public Category createNewCategory(int nameTeceId, int descriptionTeceId, int imageId, Integer parent) throws Exception {
		
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
	
	public Category findCategoryById(int categoryId) throws Exception{
		Category category = entityManager.find(Category.class, categoryId);
		return category;
	}
	
	@SuppressWarnings("unchecked")
	public List<Category> findChildrenCategories(Integer parentId) throws Exception {
		
		Category parentCategory = entityManager.find(Category.class, parentId);
		List<?> resultList = entityManager.createNamedQuery(
				Category.FIND_ALL_CHILDREN).setParameter(Category.FIND_ALL_CHILDREN_PARAM_NAME, parentCategory).getResultList();

		return (List<Category>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findTopLevelCategories() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(
				Category.FIND_ALL_TOP_LEVEL).getResultList();
		
		return (List<Category>) resultList; 
	}
	
	public Seminary insertSeminary(int dynamicContentId, int titleTextContentEntryId, int descriptionTextContentEntryId, int authorId, int difficultyValue) throws Exception {

		entityManager.getTransaction().begin();		
		DynamicContent dynamicContent = entityManager.find(DynamicContent.class, dynamicContentId);
		TextContentEntry titleEntry = entityManager.find(TextContentEntry.class, titleTextContentEntryId);
		TextContentEntry descriptionEntry = entityManager.find(TextContentEntry.class, descriptionTextContentEntryId);
		User author = entityManager.find(User.class, authorId);
		Difficulty difficulty = entityManager.find(Difficulty.class, difficultyValue);
		
		Seminary seminary = new Seminary();
		seminary.setDynamicContent(dynamicContent);
		seminary.setTitle(titleEntry);
		seminary.setDescription(descriptionEntry);
		seminary.setAuthor(author);
		seminary.setLastEditor(author);
		seminary.setDifficulty(difficulty);
		seminary.setAvailable(true);
		
		java.util.Date date= new java.util.Date();
		seminary.setDateCreated(new Timestamp(date.getTime()));
		seminary.setDateModified(new Timestamp(date.getTime()));
		
		// Insert Object
		entityManager.persist(seminary);		
		// TODO check for rollback or throw exception higher ?
		entityManager.getTransaction().commit();
		
		return seminary;
	}
}
