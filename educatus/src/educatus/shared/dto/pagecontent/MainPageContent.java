package educatus.shared.dto.pagecontent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainPageContent implements Serializable {

	// Footer
	public static class Footer implements Serializable {

		private static final long serialVersionUID = 1791695468871803204L;

	}

	// Header - MainMenu
	public static class MainMenuContent implements Serializable {

		public static enum MainMenuItemEnum {
			HOME_ITEM, 
			SEMINARS_HOME_ITEM,
			EXERCICE_HOME_ITEM,
			COMMUNITY_ITEM,
			PROFILE_ITEM,
			EXCERCICE_ITEM, 
			CREATE_SEMINAR_ITEM,
			CREATE_EXERCICE_ITEM,
			CATEGORY_ADMINISTRATION_ITEM
		}

		public static class MainMenuItemContent implements Serializable {

			private static final long serialVersionUID = 4045539245689986636L;
			private String name = null;
			private MainMenuItemEnum type = null;

			public MainMenuItemContent() {
			}

			public MainMenuItemContent(String itemName, MainMenuItemEnum type) {
				this.name = itemName;
				this.type = type;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public MainMenuItemEnum getType() {
				return type;
			}

			public void setType(MainMenuItemEnum type) {
				this.type = type;
			}
		}

		private static final long serialVersionUID = 1285932975988695050L;

		private List<MainMenuItemContent> mainMenuItemContentList = new ArrayList<MainMenuItemContent>();

		public List<MainMenuItemContent> getMainMenuItemContentList() {
			return mainMenuItemContentList;
		}

		public void setMainMenuItemContentList(List<MainMenuItemContent> mainMenuItemContentList) {
			this.mainMenuItemContentList = mainMenuItemContentList;
		}

	}

	// MainPageContent - Begin
	private static final long serialVersionUID = -3890304591665915449L;

	private MainMenuContent mainMenuContent;

	public MainMenuContent getMainMenuContent() {
		return mainMenuContent;
	}

	public void setMainMenuContent(MainMenuContent mainMenuContent) {
		this.mainMenuContent = mainMenuContent;
	}
}
