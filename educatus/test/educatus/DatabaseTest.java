package educatus;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.dao.BaseDao;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.JpaInitializer;
import educatus.shared.persist.dao.internationalization.Culture;
import educatus.shared.persist.dao.internationalization.Image;
import educatus.shared.persist.dao.internationalization.ImageExternal;
import educatus.shared.persist.dao.internationalization.ImageInternal;
import educatus.shared.persist.dao.internationalization.TextContentEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntry;


public class DatabaseTest {
	
	public static void main(String[] args) {
		
		BaseDao dao = null;
		InternationalizationDao internationalizationDao = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		dao = dbInjector.getInstance(BaseDao.class);
		internationalizationDao = dbInjector.getInstance(InternationalizationDao.class);
		
		try {
			Culture c = internationalizationDao.getCultureByCode("CA");
			System.out.println(c.getId());	
			System.out.println(c.getCode());	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		Culture culture = (Culture) dao.get(1, Culture.class);
		
		if (culture != null)
		{
			System.out.println(culture.getId());	
			System.out.println(culture.getCode());		
		}		
		
		TextContentEntry entry = (TextContentEntry) dao.get(1, TextContentEntry.class);
		
		if (entry != null)
		{
			System.out.println(entry.getId());	
			
			List<TextContentTranslationEntry> entries = entry.getTextContentTranslationEntries();
			
			for (TextContentTranslationEntry textContentTranslationEntry : entries) {
				System.out.println(textContentTranslationEntry.getTcteTranslation());	
			}
		}
				
		List<Image> imageList = (List<Image>) dao.getEntityManager().createQuery("SELECT t FROM Image t").getResultList();
		
		for (Image image : imageList) {
			
			if (image.getType().getId() == 1){
				ImageInternal internalImage = (ImageInternal) image;
				System.out.println(internalImage.getImageName());
			} else {
				ImageExternal externalImage = (ImageExternal) image;
				System.out.println(externalImage.getUrl());
			}	
		}
		
//		try {
//			EntityManager tr = dao.getEntityManager();
//			tr.getTransaction().begin();
//			
//			Culture frCulture = new Culture();
//			frCulture.setCode("FR");		
//			tr.persist(frCulture);
//			
//			Language geLanguage = new Language();
//			geLanguage.setLangCode("de");
//			tr.persist(geLanguage);
//			
//			TextContentEntry newTextContentEntry = new TextContentEntry();
//			tr.persist(newTextContentEntry);
//			
//			//TextContentTranslationEntryPK pk = new TextContentTranslationEntryPK();
//			//pk.setCultId(frCulture.getId());
//			//pk.setLangId(geLanguage.getId());
//			//pk.setTeceId(newTextContentEntry.getTeceId());
//			
//			TextContentTranslationEntry translationEntry = new TextContentTranslationEntry();
//			translationEntry.setTcteTranslation("Sauce");
//			//translationEntry.setId(pk);
//			
//			translationEntry.setCulture(frCulture);
//			translationEntry.setTextcontententry(newTextContentEntry);
//			translationEntry.setLanguage(geLanguage);
//
//			tr.persist(translationEntry);
//			
//			tr.getTransaction().commit();
//
//			System.out.println("Transaction OK");	
//			
//		} catch (Exception e) {
//			dao.getEntityManager().getTransaction().rollback();
//			e.printStackTrace();
//		}
		
		
//		Culture frCulture = new Culture();
//		frCulture.setCode("FR");		
//		dao.set(frCulture, Culture.class);
//							
//		Language geLanguage = new Language();
//		geLanguage.setLangCode("de");
//		dao.set(geLanguage, Language.class);
//		
//		TextContentEntry newTextContentEntry = new TextContentEntry();
//		dao.set(newTextContentEntry, TextContentEntry.class);
//
//		TextContentTranslationEntry translationEntry = new TextContentTranslationEntry();
//		translationEntry.setCulture(frCulture);
//		translationEntry.setLanguage(geLanguage);
//		translationEntry.setTextcontententry(newTextContentEntry);
//		translationEntry.setTcteTranslation("Sauce");
//	
//		dao.set(translationEntry, TextContentTranslationEntry.class);
		
	}
}
