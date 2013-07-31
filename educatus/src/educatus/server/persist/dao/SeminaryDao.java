package educatus.server.persist.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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

	@Inject
	private SecurityDao securityDao;

	public Category createNewCategory(int nameTeceId, int descriptionTeceId, int imageId, Integer parent) throws Exception {

		TextContentEntry nameTece = entityManager.find(TextContentEntry.class, nameTeceId);
		TextContentEntry descriptionTece = entityManager.find(TextContentEntry.class, descriptionTeceId);
		Image image = entityManager.find(Image.class, imageId);
		Category parentCategory = null;

		if (parent != null) {
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

		return category;
	}

	public Category findCategoryById(int categoryId) throws Exception {
		Category category = entityManager.find(Category.class, categoryId);
		return category;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findTopChildrenCategories(Integer parentId) throws Exception {

		Category parentCategory = entityManager.find(Category.class, parentId);
		List<?> resultList = entityManager.createNamedQuery(Category.FIND_TOP_CHILDREN).setParameter(Category.FIND_TOP_CHILDREN_PARAM_NAME, parentCategory).getResultList();

		return (List<Category>) resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Integer> findAllChildrenCategories(Integer parentId) throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Category.FIND_ALL_CHILDREN).setParameter(1,parentId).getResultList();

		return (List<Integer>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findTopLevelCategories() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Category.FIND_ALL_TOP_LEVEL).getResultList();

		return (List<Category>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Category> findAllCategories() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Category.FIND_ALL).getResultList();

		return (List<Category>) resultList;
	}

	public Difficulty findDifficultyByLevel(int level) throws Exception {
		Difficulty difficulty = entityManager.find(Difficulty.class, level);
		return difficulty;
	}

	@SuppressWarnings("unchecked")
	public List<Difficulty> findAllDifficulty() throws Exception {
		List<?> resultList = entityManager.createNamedQuery(Difficulty.FIND_ALL).getResultList();

		return (List<Difficulty>) resultList;
	}

	public Seminary findSeminaryById(int seminaryId) throws Exception {
		Seminary seminary = entityManager.find(Seminary.class, seminaryId);
		return seminary;
	}
	
	@SuppressWarnings("unchecked")
	public List<Seminary> findSeminariesByCategory(int parentCategoryId) throws Exception {
		List<Integer> categoryTreeIds = findAllChildrenCategories(parentCategoryId);
		List<Seminary> seminaries = new ArrayList<Seminary>();
		
		if(categoryTreeIds.isEmpty())
			throw new Exception("Category id doesn't exist");
		
		return (List<Seminary>) entityManager.createNamedQuery(Seminary.FIND_BY_CATEGORY).setParameter(Seminary.FIND_BY_CATEGORY_PARAM, categoryTreeIds).getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Seminary> findSeminariesByIds(List<Integer> ids) throws Exception {
		return (List<Seminary>) entityManager.createNamedQuery(Seminary.FIND_BY_IDS).setParameter(Seminary.FIND_BY_IDS_PARAM, ids).getResultList();	
	}

	@SuppressWarnings("unchecked")
	public List<Seminary> findAllSeminary() throws Exception {
		Query query = entityManager.createNamedQuery(Seminary.FIND_ALL);
		query.setHint("javax.persistence.cache.retrieveMode", "REFRESH");
		List<?> resultList = query.getResultList();

		return (List<Seminary>) resultList;
	}

	public Seminary insertSeminary(int dynamicContentId, int titleTextContentEntryId, int descriptionTextContentEntryId, int authorId, int difficultyValue) throws Exception {

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

		java.util.Date date = new java.util.Date();
		seminary.setDateCreated(new Timestamp(date.getTime()));
		seminary.setDateModified(new Timestamp(date.getTime()));

		// Insert Object
		entityManager.persist(seminary);

		return seminary;
	}

	public Seminary addCategoryToSeminary(int seminaryId, int categoryId) throws Exception {

		Seminary seminary = entityManager.find(Seminary.class, seminaryId);
		Category category = entityManager.find(Category.class, categoryId);

		if (category == null) {
			throw new Exception("Cannot add a null category to a Seminary");
		}

		seminary.getCategories().add(category);
		seminary = entityManager.merge(seminary);

		return seminary;
	}
	
	public boolean isSeminaryCompletedByUser(int semiId, String cip) throws Exception {
		
		boolean isCompleted = false;
		
		Seminary seminary = entityManager.find(Seminary.class, semiId);
		User user = securityDao.findUserByCip(cip);
		
		List<User> completedUserList = seminary.getCompletedSeminaryUsers();
		for (User user2 : completedUserList) {
			if (user2.equals(user)){
				isCompleted = true;
				break;
			}
		}
		
		return isCompleted;
	}
	
	public void addUserToCompletedSeminaryList(int semiId, String cip) throws Exception {
		
		Seminary seminary = entityManager.find(Seminary.class, semiId);
		User user = securityDao.findUserByCip(cip);
		
		if (!seminary.getCompletedSeminaryUsers().contains(user)){
			seminary.getCompletedSeminaryUsers().add(user);
		}
		
		entityManager.merge(seminary);
	}
}
