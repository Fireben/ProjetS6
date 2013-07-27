package educatus;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.businesslogic.PermissionManager;
import educatus.server.businesslogic.SearchManager;
import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.ExerciceDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.dynamiccontent.DynamicSection;
import educatus.server.persist.dao.exercice.Answer;
import educatus.server.persist.dao.exercice.AnwserChoice;
import educatus.server.persist.dao.exercice.AnwserNumeric;
import educatus.server.persist.dao.exercice.AnwserText;
import educatus.server.persist.dao.exercice.Exercice;
import educatus.server.persist.dao.exercice.ExerciceQuestion;

public class SearchManagerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		SecurityDao securityDao = null;
		DynamicContentDao dynamicContentDao = null;
		EntityManager manager = null;
		PermissionManager permManage = null;
		SearchManager searchManager = null;
		ExerciceDao exerciceDao = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule(
				"db-manager-localhost"));
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector
				.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		securityDao = dbInjector.getInstance(SecurityDao.class);
		dynamicContentDao = dbInjector.getInstance(DynamicContentDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
		permManage = dbInjector.getInstance(PermissionManager.class);		
		searchManager = dbInjector.getInstance(SearchManager.class);
		exerciceDao = dbInjector
				.getInstance(ExerciceDao.class);
		
		List<Exercice> listExer = null;
		Answer anwser = null;
		AnwserChoice aChoice = null;
		AnwserNumeric aNum = null;
		AnwserText aText = null;
		
		try
		{
			listExer = exerciceDao.findAllExercice();
			
			for(Exercice exer : listExer)
			{
				for(ExerciceQuestion exqu : exer.getExercicequestions())
				{
					anwser = exqu.getAnswer();
					
					switch(exqu.getExerciceQuestionType().getId())
					{
					case 1:
						aNum = (AnwserNumeric)anwser;
						break;
					case 2 :
						aText = (AnwserText)anwser;
						break;
					case 3 :
						aChoice = (AnwserChoice)anwser;
						break;
					}
				}
			}
			
			if(aChoice!=null)
			{
				for(DynamicSection entry : aChoice.getEqAnwserChoiceDynamicSection())
				{
					System.out.println(entry.getId());
				}
			}				
		}
		catch(Exception e)
		{
			
		}
		
		
		
//		List<Integer> list = null;
//		List<Integer> list2 = null;
//		
//		try
//		{
//			list = searchManager.SearchInSeminary("Basics of factory", false, internationalizationDao.findLanguageByCode("en"));
//			list2 = searchManager.SearchInExercice("question de ",  true, internationalizationDao.findLanguageByCode("en"));
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}

	}
}
