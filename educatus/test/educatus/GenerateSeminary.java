package educatus;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.DynamicContentDao;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SecurityDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.dynamiccontent.DynamicContent;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionAlignment;
import educatus.server.persist.dao.dynamiccontent.DynamicSectionText;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.security.User;
import educatus.server.persist.dao.seminary.Difficulty;
import educatus.server.persist.dao.seminary.Seminary;

public class GenerateSeminary {

	private static String EN_LANG = "en";
	private static String CA_CULT = "CA";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		SecurityDao securityDao = null;
		DynamicContentDao dynamicContentDao = null;
		EntityManager manager = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		securityDao = dbInjector.getInstance(SecurityDao.class);
		dynamicContentDao = dbInjector.getInstance(DynamicContentDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
		
		try {
			manager.getTransaction().begin();
			
			DynamicContent seminaryDynamicContent = dynamicContentDao.createDynamicContent();
			DynamicSectionAlignment alignment = manager.find(DynamicSectionAlignment.class, 1);
			
			TextContentTranslationEntry sectionTitle1 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Paragraph 1");
			TextContentTranslationEntry sectionText1 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage sauce nuage");
			
			dynamicContentDao.addDynamicSectionText(
					seminaryDynamicContent.getId(), 
					sectionTitle1.getTextcontententry().getId(), 
					sectionText1.getTextcontententry().getId(),
					alignment.getId());
			
			User user = securityDao.findUserByCip("beam1711");
			Difficulty difficulty = seminaryDao.findDifficultyByLevel(1);
			TextContentTranslationEntry seminaryTitle = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Title de sauce");
			TextContentTranslationEntry seminaryDesc = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "De la sauce et des nuages");
			
			Seminary seminary = seminaryDao.insertSeminary(
					seminaryDynamicContent.getId(), 
					seminaryTitle.getTextcontententry().getId(), 
					seminaryDesc.getTextcontententry().getId(), 
					user.getId(), 
					difficulty.getDifficultyValue()
				);

			manager.getTransaction().commit();
		
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		}
		
	}
}
