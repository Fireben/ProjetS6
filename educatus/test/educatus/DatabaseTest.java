package educatus;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.JpaInitializer;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.SeminaryDao;
import educatus.server.persist.dao.internationalization.Culture;
import educatus.server.persist.dao.internationalization.Image;
import educatus.server.persist.dao.internationalization.ImageContentEntry;
import educatus.server.persist.dao.internationalization.ImageContentTranslationEntry;
import educatus.server.persist.dao.internationalization.Language;
import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.server.persist.dao.internationalization.Video;
import educatus.server.persist.dao.internationalization.VideoContentEntry;
import educatus.server.persist.dao.internationalization.VideoContentTranslationEntry;
import educatus.server.persist.dao.seminary.Category;


public class DatabaseTest {
	
	public static void main(String[] args) {
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		
		try {
			
			TextContentTranslationEntry nameFr = internationalizationDao.insertTextContentTranslationEntry(
					"fr", 
					"CA", 
					"Logiciel"
			);
					
			TextContentTranslationEntry nameEn = internationalizationDao.insertTextContentTranslationEntry(
					"en", 
					"CA", 
					"Software",
					nameFr.getTextcontententry().getId()
			);
			
			TextContentTranslationEntry descriptionFr = internationalizationDao.insertTextContentTranslationEntry(
					"fr", 
					"CA", 
					"Logiciel"
			);
					
			TextContentTranslationEntry descriptionEn = internationalizationDao.insertTextContentTranslationEntry(
					"en", 
					"CA", 
					"Software",
					descriptionFr.getTextcontententry().getId()
			);
			
			Category cat = seminaryDao.createNewCategory(
					nameEn.getTextcontententry().getId(), 
					descriptionEn.getTextcontententry().getId(), 
					null
			);
		
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		
		try {
			Culture c = internationalizationDao.findCultureByCode("CA");
			System.out.println(c.getId());	
			System.out.println(c.getCode());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			List<Culture> 						allCulture = internationalizationDao.findAllCulture();
			List<Image> 						allImage = internationalizationDao.findAllImage();
			List<ImageContentEntry> 			allImageContentEntry = internationalizationDao.findAllImageContentEntry();
			List<ImageContentTranslationEntry> 	allImageContentTranslationEntry = internationalizationDao.findAllImageContentTranslationEntry();
			List<Language>						allLanguage = internationalizationDao.findAllLanguage();
			List<TextContentEntry> 				allTextContentEntry = internationalizationDao.findAllTextContentEntry();
			List<TextContentTranslationEntry> 	allTextContentTranslationEntry = internationalizationDao.findAllTextContentTranslationEntry();
			List<Video>		 					allVideo = internationalizationDao.findAllVideo();
			List<VideoContentEntry> 			allVideoContentEntry = internationalizationDao.findAllVideoContentEntry();
			List<VideoContentTranslationEntry>  allVideoContentTranslationEntry = internationalizationDao.findAllVideoContentTranslationEntry();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
