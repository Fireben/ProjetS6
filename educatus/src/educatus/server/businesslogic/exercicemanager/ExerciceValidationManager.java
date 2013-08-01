package educatus.server.businesslogic.exercicemanager;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.businesslogic.InternationalizationUtility;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.exercice.Answer;
import educatus.server.persist.dao.exercice.AnwserChoice;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.shared.dto.exercice.AnswerChoiceContent;
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
			return validAnswer.equalsIgnoreCase(currentAnswer);
		}
		else if(questionType == ExerciceQuestionType.ANSWER_NUMERIC)
		{
			//TODO A faire a moins de supprimer le type.
		}
		else if(questionType == ExerciceQuestionType.ANSWER_CHOICE)
		{
			AnwserChoice answerChoice = (AnwserChoice)answer;
			AnswerChoiceContent anwserChoiceContent = (AnswerChoiceContent)questionContent.getAnswer();		
						
			if(answerChoice.getEqAnwserChoiceDynamicSection().size() == 1)
			{
				// Single Choice
				int index = -1;
				if(anwserChoiceContent.getAnswerList().size()>0)
				{
					index = Integer.parseInt(anwserChoiceContent.getAnswerList().get(0));
				}
				
				return getAnwserIndex(answerChoice).contains(index);				
			}
			else
			{
				// Multiple Choice
				List<Integer> anwser = new ArrayList<Integer>();
				for(String str : anwserChoiceContent.getAnswerList())
				{
					anwser.add(Integer.parseInt(str));
				}
				
				List<Integer> choice = getAnwserIndex(answerChoice);
				
				if(choice.size()!=anwser.size())
				{
					return false;
				}
				else
				{
					return choice.containsAll(anwser);
				}
			}
		}

		return false;		
	}
	
	private List<Integer> getAnwserIndex(AnwserChoice anwserChoice)
	{
		ArrayList<Integer> anwserArray = new ArrayList<Integer>();		
		
		for(DynamicSection anwser : anwserChoice.getEqAnwserChoiceDynamicSection())
		{
			int index = 0;
			
			for(DynamicSection choice : anwserChoice.getDynamicContent().getDynamicSectionList())
			{
				if(choice.getId() == anwser.getId())
				{
					anwserArray.add(index);
				}
				index++;
			}
		}		
		return anwserArray;
	}
}
