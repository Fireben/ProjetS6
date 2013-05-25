package educatus;

import java.util.List;

import com.google.inject.Guice;
import com.google.inject.Injector;

import educatus.server.persist.dao.BaseDao;
import educatus.server.persist.dao.DaoModule;
import educatus.server.persist.dao.JpaInitializer;
import educatus.shared.persist.dao.internationalization.Culture;
import educatus.shared.persist.dao.internationalization.Language;
import educatus.shared.persist.dao.internationalization.TextContentEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.persist.dao.internationalization.TextContentTranslationEntryPK;


public class DatabaseTest {
	
	public static void main(String[] args) {
		
		BaseDao dao = null;
		
		Injector dbInjector = Guice.createInjector(new DaoModule("db-manager-localhost"));	 
		dbInjector.getInstance(JpaInitializer.class);
		dao = dbInjector.getInstance(BaseDao.class);

		Culture culture = (Culture) dao.get(1, Culture.class);
		
		if (culture != null)
		{
			System.out.println(culture.getId());	
			System.out.println(culture.getCode());		
		}		
		
		TextContentEntry entry = (TextContentEntry) dao.get(1, TextContentEntry.class);
		
		if (entry != null)
		{
			System.out.println(entry.getTeceId());	
			
			List<TextContentTranslationEntry> entries = entry.getTextcontenttranslationentries();
			
			for (TextContentTranslationEntry textContentTranslationEntry : entries) {
				System.out.println(textContentTranslationEntry.getTcteTranslation());	
			}
		}		
				
		Culture frCulture = new Culture();
		frCulture.setCode("FR");		
		dao.set(frCulture, Culture.class);
							
		Language geLanguage = new Language();
		geLanguage.setLangCode("ge");
		dao.set(geLanguage, Language.class);
		
		TextContentEntry newTextContentEntry = new TextContentEntry();
		dao.set(newTextContentEntry, TextContentEntry.class);

		TextContentTranslationEntry translationEntry = new TextContentTranslationEntry();
		translationEntry.setCulture(frCulture);
		translationEntry.setLanguage(geLanguage);
		translationEntry.setTextcontententry(newTextContentEntry);
		translationEntry.setTcteTranslation("Sauce");
	
		dao.set(translationEntry, TextContentTranslationEntry.class);
		
	}
}
