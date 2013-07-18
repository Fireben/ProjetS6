package educatus.server.persist.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.exercice.AnwserNumeric;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.exercice.ExerciceQuestion;
import educatus.server.persist.dao.exercice.ExerciceQuestionType;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.server.persist.dao.seminary.Seminary;

@Singleton
public class ExerciceDao {

	@Inject
	private EntityManager entityManager;
	
	@Inject 
	private SeminaryDao seminaryDao;
	
	
	@SuppressWarnings("unchecked")
	public List<Exercice> findAllExercice() throws Exception {
		Query query = entityManager.createNamedQuery(Exercice.FIND_ALL);
		query.setHint("javax.persistence.cache.retrieveMode", "REFRESH");
		List<?> resultList = query.getResultList();
		return (List<Exercice>) resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Exercice> findExercicesByCategory(int parentId) throws Exception {
		List<Integer> categoryTreeIds = seminaryDao.findAllChildrenCategories(parentId);
		List<Exercice> exercices = new ArrayList<Exercice>();
		
		if(categoryTreeIds.isEmpty())
			throw new Exception("Category id doesn't exist");
		
		return (List<Exercice>) entityManager.createNamedQuery(Exercice.FIND_BY_CATEGORY).setParameter(Exercice.FIND_BY_CATEGORY_PARAM, categoryTreeIds).getResultList();	
	}
	
	public Exercice insertExercice(int titleTextContentEntryId, int descriptionTextContentEntryId, int authorId, int difficultyValue) throws Exception {

		TextContentEntry titleEntry = entityManager.find(TextContentEntry.class, titleTextContentEntryId);
		TextContentEntry descriptionEntry = entityManager.find(TextContentEntry.class, descriptionTextContentEntryId);
		User author = entityManager.find(User.class, authorId);
		Difficulty difficulty = entityManager.find(Difficulty.class, difficultyValue);

		Exercice exercice = new Exercice();
		exercice.setTitle(titleEntry);
		exercice.setDescription(descriptionEntry);
		exercice.setAuthor(author);
		exercice.setLastEditor(author);
		exercice.setDifficultyValue(difficulty);
		exercice.setAvailable(true);

		java.util.Date date = new java.util.Date();
		exercice.setDateCreated(new Timestamp(date.getTime()));
		exercice.setDateModified(new Timestamp(date.getTime()));

		// Insert Object
		entityManager.persist(exercice);

		return exercice;
	}
	
	public Exercice addCategoryToExercice(int exerciceId, int categoryId) throws Exception {

		Exercice exercice = entityManager.find(Exercice.class, exerciceId);
		Category category = entityManager.find(Category.class, categoryId);

		if (category == null) {
			throw new Exception("Cannot add a null category to a Seminary");
		}

		exercice.getCategoryList().add(category);
		exercice = entityManager.merge(exercice);

		return exercice;
	}
	
	public void addTextExerciceQuestionToExercice(int exerciceId, int questionContextDynamicContentId, int questionTextContentEntryId, int score, int responseValueTextContentEntryId, int sequence)
	{
		Exercice exercice = entityManager.find(Exercice.class, exerciceId);
		DynamicContent questionContext = entityManager.find(DynamicContent.class, questionContextDynamicContentId);
		TextContentEntry questionTextContentEntry = entityManager.find(TextContentEntry.class, questionTextContentEntryId);
		TextContentEntry responseValueTextContentEntry = entityManager.find(TextContentEntry.class, responseValueTextContentEntryId);
		// Answer Text => type 2
		ExerciceQuestionType questionType = entityManager.find(ExerciceQuestionType.class, 2);
		
		ExerciceQuestion exerciceQuestion = new ExerciceQuestion();
		exerciceQuestion.setDynamicContent(questionContext);
		exerciceQuestion.setQuestion(questionTextContentEntry);
		exerciceQuestion.setExercice(exercice);
		exerciceQuestion.setExercicequestiontype(questionType);
		exerciceQuestion.setScore(score);
		exerciceQuestion.setSequence(sequence);
		exercice.getExercicequestions().add(exerciceQuestion);
		entityManager.persist(exerciceQuestion);
		
		AnwserText anwserText = new AnwserText();
		anwserText.setExerciceQuestionType(questionType);
		anwserText.setId(exerciceQuestion.getId());
		anwserText.setValue(responseValueTextContentEntry);
		anwserText.setExerciceQuestion(exerciceQuestion);
		exerciceQuestion.setAnswer(anwserText);
		entityManager.persist(anwserText);
	}
	
	public void addNumericExerciceQuestionToExercice(int exerciceId, int questionContextDynamicContentId, int questionTextContentEntryId, int score, int answer, int sequence)
	{
		Exercice exercice = entityManager.find(Exercice.class, exerciceId);
		DynamicContent questionContext = entityManager.find(DynamicContent.class, questionContextDynamicContentId);
		TextContentEntry questionTextContentEntry = entityManager.find(TextContentEntry.class, questionTextContentEntryId);
		// Answer Numeric => type 1
		ExerciceQuestionType questionType = entityManager.find(ExerciceQuestionType.class, 1);
		
		ExerciceQuestion exerciceQuestion = new ExerciceQuestion();
		exerciceQuestion.setDynamicContent(questionContext);
		exerciceQuestion.setQuestion(questionTextContentEntry);
		exerciceQuestion.setExercice(exercice);
		exerciceQuestion.setExercicequestiontype(questionType);
		exerciceQuestion.setScore(score);
		exerciceQuestion.setSequence(sequence);
		exercice.getExercicequestions().add(exerciceQuestion);
		entityManager.persist(exerciceQuestion);
		
		AnwserNumeric anwserNumeric = new AnwserNumeric();
		anwserNumeric.setExerciceQuestionType(questionType);
		anwserNumeric.setId(exerciceQuestion.getId());
		anwserNumeric.setValue(answer);
		anwserNumeric.setExerciceQuestion(exerciceQuestion);
		exerciceQuestion.setAnswer(anwserNumeric);
		entityManager.persist(anwserNumeric);
	}
}
