package educatus.server.businesslogic.uibuilder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.client.NameTokens;
import educatus.server.persist.dao.InternationalizationDao;
import educatus.server.persist.dao.internationalization.TextContentTranslationEntry;
import educatus.shared.dto.pagecontent.MainPageContent;
import educatus.shared.dto.pagecontent.ViewModeEnum;
import educatus.shared.dto.pagecontent.MainPageContent.MainMenuContent;
import educatus.shared.dto.pagecontent.MainPageContent.MainMenuContent.MainMenuItemContent;
import educatus.shared.dto.pagecontent.MainPageContent.MainMenuContent.MainMenuItemEnum;

@Singleton
public class MainPageContentBuilder {

	/* Text */
	private static final int HOME_MAIN_MENU_ITEM = -11000;
	private static final int SEMINARS_MAIN_MENU_ITEM = -11001;
	//private static final int PROFILE_MAIN_MENU_ITEM = -11002;
	//private static final int VIEW_SEMINARY_MAIN_MENU_ITEM = -11003;
	private static final int CREATE_SEMINARY_MAIN_MENU_ITEM = -11004;
	private static final int CATEGORY_ADMINISTRATION_MAIN_MENU_ITEM = -11005;
	private static final int CREATE_EXERCICE_MAIN_MENU_ITEM = -11006;
	private static final int COMMUNITY_MAIN_MENU_ITEM = -11007;
	private static final int EXERCICES_MAIN_MENU_ITEM = -11008;

	@Inject
	private InternationalizationDao interDao;

	public MainPageContent buildMainPageContent(String culture, String language, ViewModeEnum mode) {

		if (mode == ViewModeEnum.ADMIN){
			return buildAdminMainPageContent(culture, language);
		} else if (mode == ViewModeEnum.USER){
			return buildUserMainPageContent(culture, language);
		} else {
			return buildGuestMainPageContent(culture, language);
		}
	}

	
	private MainPageContent buildGuestMainPageContent(String culture, String language) {

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
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.HOME_ITEM));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINARS_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.SEMINARS_HOME_ITEM));
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, EXERCICES_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.EXERCICE_HOME_ITEM));

		mainPageContent.setMainMenuContent(mainMenuContent);
		
		return mainPageContent;
	}


	private MainPageContent buildUserMainPageContent(String culture, String language) {

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
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.HOME_ITEM));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, SEMINARS_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.SEMINARS_HOME_ITEM));
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, EXERCICES_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.EXERCICE_HOME_ITEM));

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, COMMUNITY_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.COMMUNITY_ITEM));
		
		mainPageContent.setMainMenuContent(mainMenuContent);
		
		return mainPageContent;
	}
	
	private MainPageContent buildAdminMainPageContent(String culture, String language) {

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

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CREATE_SEMINARY_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.CREATE_SEMINAR_ITEM));
		
		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CREATE_EXERCICE_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.CREATE_EXERCICE_ITEM));	

		textContentTranslationEntry = interDao.findTextContentTranslationEntryById(languageId, cultureId, CATEGORY_ADMINISTRATION_MAIN_MENU_ITEM);
		text = textContentTranslationEntry == null ? "" : textContentTranslationEntry.getTcteTranslation();
		mainMenuContent.getMainMenuItemContentList().add(new MainMenuItemContent(text, MainMenuItemEnum.CATEGORY_ADMINISTRATION_ITEM));		

		
		mainPageContent.setMainMenuContent(mainMenuContent);
		
		return mainPageContent;
	}
}
