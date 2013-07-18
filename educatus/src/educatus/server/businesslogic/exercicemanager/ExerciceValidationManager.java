package educatus.server.businesslogic.exercicemanager;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.exercice.Answer;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.shared.dto.exercice.AnswerTextContent;
import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.dto.exercice.ExerciceQuestionType;

@Singleton
public class ExerciceValidationManager {
	@Inject
	private EntityManager entityManager;
	
	public boolean validateAnswer(ExerciceQuestionContent questionContent, String culture, String language) {
		Answer answer = entityManager.find(Answer.class, questionContent.getAnswer().getId());
		
		ExerciceQuestionType questionType = questionContent.getAnswer().getExerciceQuestionType();
		if(questionType == ExerciceQuestionType.ANSWER_TEXT) {
			String validAnswer = InternationalizationUtility.
					getTranslationEntry(((AnwserText)answer).getValue(), culture, language).
					getTcteTranslation();
			String currentAnswer = ((AnswerTextContent)questionContent.getAnswer()).getTextAnswer();
			return validAnswer.equals(currentAnswer);
		}

		return false;		
	}
}
