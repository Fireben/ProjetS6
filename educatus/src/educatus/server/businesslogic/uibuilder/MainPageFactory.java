package educatus.server.businesslogic.uibuilder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.dto.MainPageContent;
import educatus.shared.dto.MainPageContent.MainMenuContent;
import educatus.shared.dto.MainPageContent.MainMenuContent.MainMenuItemContent;

@Singleton
public class MainPageFactory {

	/* Text */
	private static final int HOME_MAIN_MENU_ITEM = -11000;
	private static final int SEMINARS_MAIN_MENU_ITEM = -11001;
	private static final int PROFILE_MAIN_MENU_ITEM = -11002;
	private static final int VIEW_SEMINARY_MAIN_MENU_ITEM = -11003;
	private static final int CREATE_SEMINARY_MAIN_MENU_ITEM = -11004;

	@Inject
	private InternationalizationDao interDao;

	public MainPageContent createMainPageContent(String culture, String language) {

		int cultureId;
		int languageId;
		try {
			cultureId = interDao.findCultureByCode(culture).getId();
			languageId = interDao.findLanguageByCode(language).getId();

		} catch (Exception e) {
			// TODO Manage Exceptions
			e.printStackTrace();
			return null;
		}

		MainPageContent mainPageContent = new MainPageContent();
		MainMenuContent mainMenuContent = new MainMenuContent();

		TextContentTranslationEntry textContentTranslationEntry = null;
		String text = "";

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, HOME_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.setHomeItem(new MainMenuItemContent(text));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINARS_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.setSeminaryItem(new MainMenuItemContent(text));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, PROFILE_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.setProfilItem(new MainMenuItemContent(text));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, VIEW_SEMINARY_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.setViewerItem(new MainMenuItemContent(text));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CREATE_SEMINARY_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.setEditorItem(new MainMenuItemContent(text));

		mainPageContent.setMainMenuContent(mainMenuContent);

		return mainPageContent;
	}

}
