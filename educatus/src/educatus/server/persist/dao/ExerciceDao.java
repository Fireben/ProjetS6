package educatus.server.persist.dao;

import java.sql.Timestamp;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.exercice.ExerciceQuestion;
import educatus.server.persist.dao.exercice.ExerciceQuestionType;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Category;
import educatus.server.persist.dao.seminary.Difficulty;

@Singleton
public class ExerciceDao {

	@Inject
	private EntityManager entityManager;
	
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
	
	public void addTextExerciceQuestionToExercice(int exerciceId, int questionContextDynamicContentId, int score, int responseValueTextContentEntryId, int sequence)
	{
		Exercice exercice = entityManager.find(Exercice.class, exerciceId);
		DynamicContent questionContext = entityManager.find(DynamicContent.class, questionContextDynamicContentId);
		TextContentEntry responseValueTextContentEntry = entityManager.find(TextContentEntry.class, responseValueTextContentEntryId);
		// Answer Text => type 2
		ExerciceQuestionType questionType = entityManager.find(ExerciceQuestionType.class, 2);
		
		ExerciceQuestion exerciceQuestion = new ExerciceQuestion();
		exerciceQuestion.setDynamicContent(questionContext);
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
		exerciceQuestion.setAnswer(anwserText);
		entityManager.persist(anwserText);
	}
}
