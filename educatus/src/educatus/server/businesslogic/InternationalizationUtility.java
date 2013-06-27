package educatus.server.businesslogic;

import educatus.server.persist.dao.internationalization.TextContentEntry;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;

public class InternationalizationUtility {
	
	public static TextContentTranslationEntry getTranslationEntry(TextContentEntry entry, String culture, String language){
		TextContentTranslationEntry foundEntry = null;
		
		for (TextContentTranslationEntry translationEntry : entry.getTextContentTranslationEntries()){
			if (	translationEntry.getCulture().getCode().equalsIgnoreCase(culture) 
				 && translationEntry.getLanguage().getCode().equalsIgnoreCase(language)){
				foundEntry = translationEntry;
				break;
			}
		}
		
		return foundEntry;
	}
}
