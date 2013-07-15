package educatus.server.businesslogic.exercicemanager;

import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.DynamicContentAdapter;
import educatus.server.businesslogic.ExerciceAdapter;
import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.exercice.AnwserChoice;
import educatus.server.persist.dao.exercice.AnwserNumeric;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.exercice.ExerciceQuestion;
import educatus.shared.dto.dynamiccontent.AbstractDynamicSection;
import educatus.shared.dto.exercice.AnswerChoiceContent;
import educatus.shared.dto.exercice.AnswerNumericContent;
import educatus.shared.dto.exercice.AnswerTextContent;
import educatus.shared.dto.exercice.ExerciceContent;
import educatus.shared.dto.exercice.ExerciceCoreContent;
import educatus.shared.dto.exercice.ExerciceQuestionContent;
import educatus.shared.dto.exercice.ExerciceQuestionType;

@Singleton
public class ExerciceContentBuilder {

	@Inject
	private EntityManager entityManager;
	
	public ExerciceContent buildExerciceContent(int exerciceId, String culture, String language) {

		Exercice exercice = entityManager.find(Exercice.class, exerciceId);
		ExerciceContent content = parseExercice(exercice, culture, language);
		return content;
	}

	private ExerciceContent parseExercice(Exercice exercice, String culture, String language) {
		
		ExerciceContent content = new ExerciceContent();
		ExerciceCoreContent coreContent = ExerciceAdapter.exerciceToExerciceCoreContent(exercice, culture, language);
		
		for (ExerciceQuestion exerciceQuestion : exercice.getExercicequestions()){
			
			ExerciceQuestionContent exerciceQuestionContent = new ExerciceQuestionContent();

			List<AbstractDynamicSection> abstractDynamicSectionList = DynamicContentAdapter.getAbstractDynamicSectionFromDynamicContent(exerciceQuestion.getDynamicContent(), culture, language);
			exerciceQuestionContent.setQuestionContext(abstractDynamicSectionList);
			exerciceQuestionContent.setId(exerciceQuestion.getId());
			exerciceQuestionContent.setScore(exerciceQuestion.getScore());
			exerciceQuestionContent.setSequence(exerciceQuestion.getSequence());
			exerciceQuestionContent.setQuestion(InternationalizationUtility.getTranslationEntry(exerciceQuestion.getQuestion(), culture, language).getTcteTranslation());
			
			switch(exerciceQuestion.getExerciceQuestionType().getId())
			{
				case 1: 
					exerciceQuestionContent.setQuestionType(ExerciceQuestionType.ANSWER_NUMERIC);
					AnwserNumeric anwserNumeric = (AnwserNumeric) exerciceQuestion.getAnswer();
					
					AnswerNumericContent answerNumericContent = new AnswerNumericContent();
					answerNumericContent.setId(anwserNumeric.getId());
					// TODO, do we send answer to client side ?
					//answerNumericContent.setNumericAnswer(anwserNumeric.getValue());
					exerciceQuestionContent.setAnswer(answerNumericContent);
					
					break;
				case 2: 
					exerciceQuestionContent.setQuestionType(ExerciceQuestionType.ANSWER_TEXT);
					AnwserText anwserText = (AnwserText) exerciceQuestion.getAnswer();
					
					AnswerTextContent answerTextContent = new AnswerTextContent();
					answerTextContent.setId(anwserText.getId());
					// TODO, do we send answer to client side ?
					//answerTextContent.setTextAnswer(InternationalizationUtility.getTranslationEntry(anwserText.getValue(), culture, language).getTcteTranslation());
					exerciceQuestionContent.setAnswer(answerTextContent);
					
					break;
				case 3: 
					// TODO, finish AnswerChoice
					exerciceQuestionContent.setQuestionType(ExerciceQuestionType.ANSWER_CHOICE);
					AnwserChoice anwserChoice = (AnwserChoice) exerciceQuestion.getAnswer();
					
					AnswerChoiceContent answerChoiceContent = new AnswerChoiceContent();
					answerChoiceContent.setId(anwserChoice.getId());
					
					exerciceQuestionContent.setAnswer(answerChoiceContent);
					break;
				default :
					break;
				
			}			
			content.getQuestionList().add(exerciceQuestionContent);
		}
		
		content.setCoreContent(coreContent);
		return content;
	}
}
