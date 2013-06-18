package educatus.server.businesslogic.uibuilder;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import educatus.server.persist.dao.InternationalizationDao;
import educatus.shared.dto.MainPageContent;
import educatus.shared.dto.MainPageContent.MainMenuContent;
import educatus.shared.dto.MainPageContent.MainMenuContent.MainMenuItemContent;

@Singleton
public class MainPageFactory {
	
	@Inject
	private InternationalizationDao interDao;
	
	
	public MainPageContent createMainPageContent(String culture, String language) {
		MainPageContent mainPageContent = new MainPageContent();
		MainMenuContent mainMenuContent = new MainMenuContent();
		
		if (language.equalsIgnoreCase("en")){
			mainMenuContent.setHomeItem(new MainMenuItemContent("HOME"));
			mainMenuContent.setSeminaryItem(new MainMenuItemContent("SEMINARS"));
			mainMenuContent.setProfilItem(new MainMenuItemContent("PROFIL"));
			mainMenuContent.setViewerItem(new MainMenuItemContent("VIEW SEMINARY"));
			mainMenuContent.setEditorItem(new MainMenuItemContent("CREATE SEMINARY"));
		} else {
			mainMenuContent.setHomeItem(new MainMenuItemContent("ACCUEIL"));
			mainMenuContent.setSeminaryItem(new MainMenuItemContent("SÉMINAIRES"));
			mainMenuContent.setProfilItem(new MainMenuItemContent("PROFILE"));
			mainMenuContent.setViewerItem(new MainMenuItemContent("VISIONNER"));
			mainMenuContent.setEditorItem(new MainMenuItemContent("CRÉER"));
		}		
		
		mainPageContent.setMainMenuContent(mainMenuContent);
		
		return mainPageContent;
	}
	
}
