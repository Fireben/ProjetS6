package educatus;

import java.util.List;

import javax.persistence.EntityManager;

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


public class DatabaseTest {
	
	public static void main(String[] args) {
		
		InternationalizationDao internationalizationDao = null;
		SeminaryDao seminaryDao = null;
		EntityManager manager = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		seminaryDao = dbInjector.getInstance(SeminaryDao.class);
		manager = dbInjector.getInstance(EntityManager.class);
//		
//		Category parentCat = null;
//		Category child1 = null;
//		Category child2 = null;
//		
//		try {
//			
//			TextContentTranslationEntry parentNameEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Computer Engineering"
//			);
//								
//			TextContentTranslationEntry parentDescriptionEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Computer Engineering"
//			);
//			
//			TextContentTranslationEntry child1NameEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Software"
//			);
//								
//			TextContentTranslationEntry child1DescriptionEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Software"
//			);
//			
//			TextContentTranslationEntry child2NameEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Hardware"
//			);
//								
//			TextContentTranslationEntry child2DescriptionEn = internationalizationDao.insertTextContentTranslationEntry(
//					"en", 
//					"CA", 
//					"Hardware"
//			);
//					
//			// Url des images
//			ImageExternal external1 = internationalizationDao.insertImageExternal("sauceNuage1");
//			ImageExternal external2 = internationalizationDao.insertImageExternal("sauceNuage2");
//			ImageExternal external3 = internationalizationDao.insertImageExternal("sauceNuage3");
//			
//			parentCat = seminaryDao.createNewCategory(
//					parentNameEn.getTextcontententry().getId(), 
//					parentDescriptionEn.getTextcontententry().getId(), 
//					external1.getId(),
//					null
//			);
//			
//			child1 = seminaryDao.createNewCategory(
//					child1NameEn.getTextcontententry().getId(), 
//					child1DescriptionEn.getTextcontententry().getId(), 
//					external2.getId(),
//					parentCat.getId()
//			);
//			
//			child2 = seminaryDao.createNewCategory(
//					child2NameEn.getTextcontententry().getId(), 
//					child2DescriptionEn.getTextcontententry().getId(), 
//					external3.getId(),
//					parentCat.getId()
//			);
//		
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
			
		
		//Category foundParentCat = manager.find(Category.class, 42);
		//List<Category> listCat = seminaryDao.findChildrensCategories(foundParentCat.getId());
		
		
//		for (Category category : listCat) {
//			TextContentEntry entry = category.getName();
//			List<TextContentTranslationEntry> entries = entry.getTextContentTranslationEntries();
//			for (TextContentTranslationEntry textContentTranslationEntry : entries) {
//				System.out.println(textContentTranslationEntry.getTcteTranslation());
//			}			
//		}		
		
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
