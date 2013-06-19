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
import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.server.persist.dao.internationalization.ImageExternal;
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
					EN_LANG, CA_CULT, "Abstract Factory");
			TextContentTranslationEntry sectionText1 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Provides an interface for creating families of related or dependant objects without specifying their concrete classes.");
			
			dynamicContentDao.addDynamicSectionText(
					seminaryDynamicContent.getId(), 
					sectionTitle1.getTextcontententry().getId(), 
					sectionText1.getTextcontententry().getId(),
					alignment.getId(),
					0);
			
			TextContentTranslationEntry imageDescription3 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Factory Example");
			ImageExternal imageExternal = internationalizationDao.insertImageExternal("images/abstractFactory.png");
			ImageContentTranslationEntry imageEntry = internationalizationDao.insertImageTranslationEntry(EN_LANG, CA_CULT, imageExternal.getId());
			
			dynamicContentDao.addDynamicSectionImage(
					seminaryDynamicContent.getId(), 
					imageDescription3.getTextcontententry().getId(), 
					imageEntry.getImagecontententry().getId(),
					alignment.getId(),
					1);	
			
			TextContentTranslationEntry sectionTitle2 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Factory Method");
			TextContentTranslationEntry sectionText2 = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Define an interface for creating an object, but let subclass defice which class to instanciate. Factory Method lets a class defer instantiation to the subclasses.");
			
			dynamicContentDao.addDynamicSectionText(
					seminaryDynamicContent.getId(), 
					sectionTitle2.getTextcontententry().getId(), 
					sectionText2.getTextcontententry().getId(),
					alignment.getId(),
					2);			
			
			User user = securityDao.findUserByCip("beam1711");
			Difficulty difficulty = seminaryDao.findDifficultyByLevel(1);
			TextContentTranslationEntry seminaryTitle = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Design patterns : Factory");
			TextContentTranslationEntry seminaryDesc = internationalizationDao.insertTextContentTranslationEntry(
					EN_LANG, CA_CULT, "Basics of factory pattern");
			
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
