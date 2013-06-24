package educatus.shared.dto;

import java.io.Serializable;

public class MainPageContent implements Serializable {

	// Footer
	public static class Footer implements Serializable {

		private static final long serialVersionUID = 1791695468871803204L;

	}

	// Header - MainMenu
	public static class MainMenuContent implements Serializable {
		
		public static enum MainMenuItemEnum {
			HOME_ITEM
		}

		public static class MainMenuItemContent implements Serializable {

			private static final long serialVersionUID = 4045539245689986636L;
			private String name;
			
			public MainMenuItemContent() {
			}
			
			public MainMenuItemContent(String itemName){
				this.name = itemName;
			}

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

		private static final long serialVersionUID = 1285932975988695050L;

		private MainMenuItemContent homeItem;
		private MainMenuItemContent seminaryItem;
		private MainMenuItemContent profilItem;
		private MainMenuItemContent viewerItem;
		private MainMenuItemContent editorItem;

		public MainMenuItemContent getHomeItem() {
			return homeItem;
		}

		public void setHomeItem(MainMenuItemContent homeItem) {
			this.homeItem = homeItem;
		}

		public MainMenuItemContent getSeminaryItem() {
			return seminaryItem;
		}

		public void setSeminaryItem(MainMenuItemContent seminaryItem) {
			this.seminaryItem = seminaryItem;
		}

		public MainMenuItemContent getProfilItem() {
			return profilItem;
		}

		public void setProfilItem(MainMenuItemContent profilItem) {
			this.profilItem = profilItem;
		}

		public MainMenuItemContent getViewerItem() {
			return viewerItem;
		}

		public void setViewerItem(MainMenuItemContent viewerItem) {
			this.viewerItem = viewerItem;
		}

		public MainMenuItemContent getEditorItem() {
			return editorItem;
		}

		public void setEditorItem(MainMenuItemContent editorItem) {
			this.editorItem = editorItem;
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
