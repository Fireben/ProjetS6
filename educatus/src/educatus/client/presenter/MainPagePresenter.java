/**
 * Copyright 2011 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package educatus.client.presenter;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyEvent;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.LockInteractionEvent;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.ResetPresentersEvent;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

import educatus.client.EducatusLocale;
import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.events.PageChangingEvent.PageChangeHandler;
import educatus.client.ui.Footer;
import educatus.client.ui.MainMenu;
import educatus.shared.dto.pagecontent.MainPageContent;
import educatus.shared.dto.pagecontent.MainPageContent.MainMenuContent.MainMenuItemContent;
import educatus.shared.dto.pagecontent.MainPageContent.MainMenuContent.MainMenuItemEnum;
import educatus.shared.dto.pagecontent.ViewModeEnum;
import educatus.shared.dto.user.UserCoreContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.LoginRequest;
import educatus.shared.services.requestservice.request.MainPageContentRequest;
import educatus.shared.services.requestservice.response.LoginResponse;
import educatus.shared.services.requestservice.response.LoginResponse.LoginStatus;
import educatus.shared.services.requestservice.response.MainPageContentResponse;

/**
 * This is the top-level presenter of the hierarchy. Other presenters reveal
 * themselves within this presenter.
 * <p />
 * The goal of this sample is to show how to use nested presenters. These can be
 * useful to decouple multiple presenters that need to be displayed on the
 * screen simultaneously.
 * 
 * @author Christian Goudreau
 */
public class MainPagePresenter extends Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {
	/**
	 * {@link MainPagePresenter}'s proxy.
	 */
	@ProxyStandard
	public interface MyProxy extends Proxy<MainPagePresenter> {
	}

	/**
	 * {@link MainPagePresenter}'s view.
	 */
	public interface MyView extends View {
		public FlowPanel getMainContentPanel();

		// TODO, pourquoi un header panel, si on a accès direct au menuPanel ???
		public FlowPanel getHeaderPanel();

		public MainMenu getMainMenu();

		public Footer getFooterPanel();

		void showLoading(boolean visibile);
	}

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT.create(RequestService.class);

	private class TranslateClickHandler implements ClickHandler {
		private String culture;
		private String language;

		public TranslateClickHandler(String culture, String language) {
			this.culture = culture;
			this.language = language;
		}

		@Override
		public void onClick(ClickEvent event) {
			locale.setCulture(this.culture);
			locale.setLanguage(this.language);

			// TODO Modify behavior
			ResetPresentersEvent.fire(MainPagePresenter.this);
		}
	}
	
	private class LogoutButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			if (request.getViewMode() == ViewModeEnum.ADMIN || placeManager.getCurrentPlaceRequest().getNameToken() == NameTokens.getProfil()){
				placeManager.revealPlace(new PlaceRequest(NameTokens.getHomePage()));
				requestView(ViewModeEnum.USER);
			}		
			// Display the login Ui in the MainMenu
			displayLoginUi();
			//Clear the existing Logged User
			
		}
	}
	
	private class AdminModeButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			// Comportement d'un toggle button ?
			if (request.getViewMode() == ViewModeEnum.ADMIN){
				placeManager.revealPlace(new PlaceRequest(NameTokens.getHomePage()));
				requestView(ViewModeEnum.USER);
			} else {
				placeManager.revealPlace(new PlaceRequest(NameTokens.getSeminaryEdit()));
				requestView(ViewModeEnum.ADMIN);
			}
		}
	}

	private class ViewProfileButtonClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			placeManager.revealPlace(new PlaceRequest(NameTokens.getProfil()));			
		}
	}
	
	private class AbstractRequestHandler implements AsyncCallback<AbstractResponse> {

		@Override
		public void onSuccess(AbstractResponse result) {
			if (result.GetResponseType() == ResponseTypeEnum.MAIN_PAGE_CONTENT_RESPONSE) {
				MainPageContentResponse response = (MainPageContentResponse) result;						
				fillPageWithContent(response.getMainPageContent());
				
				if (response.getViewMode() == ViewModeEnum.ADMIN) {
					getView().getMainMenu().getLogInProfilUi().getDropDownUi().setAdminButtonText("User");
				} else {
					getView().getMainMenu().getLogInProfilUi().getDropDownUi().setAdminButtonText("Admin");
				}
			}
		}

		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
		}
	}

	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy, final PlaceManager placeManager) {
		super(eventBus, view, proxy);
		this.placeManager = placeManager;
	}

	@Inject
	private EducatusLocale locale;

	private PlaceManager placeManager;

	private MainPageContentRequest request = new MainPageContentRequest();
	
	final Button confirmButton = new Button();
	final TextBox boxUserName = new TextBox(); 
	
	private int logInAttempt = 1;
	
	private DialogBox dialogBox = createLoginDialogBox();
	
	private String activePage = null;

	private class ExtendedDialogBox extends DialogBox {

	    @Override
	    protected void onPreviewNativeEvent(NativePreviewEvent event) {
	        super.onPreviewNativeEvent(event);

	        switch (event.getTypeInt()) {
        	
	            case Event.ONKEYDOWN:
	                if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ESCAPE) {
	                    hide();
	                } else if (event.getNativeEvent().getKeyCode() == KeyCodes.KEY_ENTER) {
	                	confirmButton.click();
	                }
	                break;
	        }
	    }
	}

	private AbstractRequestHandler requestHandler = new AbstractRequestHandler();
	
	@Override
	protected void revealInParent() {
		RevealRootContentEvent.fire(this, this);
	}

	/**
	 * We display a short lock message whenever navigation is in progress.
	 * 
	 * @param event
	 *            The {@link LockInteractionEvent}.
	 */
	@ProxyEvent
	public void onLockInteraction(LockInteractionEvent event) {
		getView().showLoading(event.shouldLock());
	}

	@Override
	protected void onBind() {
		super.onBind();
		getView().getMainMenu().getLogInUi().getLogInLink().addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				dialogBox.center();
				dialogBox.show();
				boxUserName.setFocus(true);
			}
		});
		
		// Set click handlers for LogInProfilUi
		getView().getMainMenu().getLogInProfilUi().getLogOutLink().addClickHandler(new LogoutButtonClickHandler());
		getView().getMainMenu().getLogInProfilUi().getDropDownUi().setAdminButtonHandler(new AdminModeButtonClickHandler());
		getView().getMainMenu().getLogInProfilUi().getDropDownUi().setProfilButtonHandler(new ViewProfileButtonClickHandler());

		// Default -> display Login
		displayLoginUi();
		getView().getHeaderPanel().add(getView().getMainMenu());

		getView().getFooterPanel().getEnglishButton().addClickHandler(new TranslateClickHandler("CA", "en"));
		getView().getFooterPanel().getFrenchButton().addClickHandler(new TranslateClickHandler("CA", "fr"));

		addRegisteredHandler(PageChangingEvent.getType(), new PageChangeHandler() {
			@Override
			public void onPageChange(PageChangingEvent event) {
				activePage = event.getMessage();
				setActiveMenuItem(activePage);
			}
		});
	}

	@Override
	protected void onReset() {
		super.onReset();

		if (request.getCulture() != locale.getCulture() || request.getLanguage() != locale.getLanguage()) {
			request.setCulture(locale.getCulture());
			request.setLanguage(locale.getLanguage());
			requestService.sendRequest(request, requestHandler);
		}
	}

	private void requestView(ViewModeEnum viewMode) {
		request.setViewMode(viewMode);
		requestService.sendRequest(request, requestHandler);
	}
	
	private void displayLoginUi(){
		getView().getMainMenu().getLogInProfilUi().setVisible(false);
		getView().getMainMenu().getLogInUi().setVisible(true);	
	}
	
	private void displayLoginProfilUi(){
		getView().getMainMenu().getLogInProfilUi().setVisible(true);
		getView().getMainMenu().getLogInUi().setVisible(false);	
	}
	
	private void fillPageWithContent(MainPageContent content){
		// We clear the MainMenuList
		getView().getMainMenu().clearMainMenuList();
		
		MainMenu mainMenu = getView().getMainMenu();
		// We fill the MainMenuList
		for (MainMenuItemContent mainMenuItemContent : content.getMainMenuContent().getMainMenuItemContentList()) {
			
			String name = mainMenuItemContent.getName();
			String nameToken = getNameTokenFromMenuItemType(mainMenuItemContent.getType());
			// Append to the MainMenu
			mainMenu.appendMainMenuItem(name, nameToken);
		}
		mainMenu.setActiveMenuItem(activePage);
	}
	
	private String getNameTokenFromMenuItemType(MainMenuItemEnum type){
		String nameToken = null;
		
		switch (type) {
			case HOME_ITEM:
				nameToken = NameTokens.getHomePage();
				break;
			case SEMINARS_HOME_ITEM:
				nameToken = NameTokens.getSeminarHomePage();
				break;
			case PROFILE_ITEM:
				nameToken = NameTokens.getProfil();
				break;
			case CREATE_SEMINAR_ITEM:
				nameToken = NameTokens.getSeminaryEdit();
				break;
			case CATEGORY_ADMINISTRATION_ITEM:
				nameToken = NameTokens.getCategoryAdministration();
				break;
			case CREATE_EXERCICE_ITEM:
				nameToken = NameTokens.getExerciceEdit();
				break;
			// Default Token leads to HomePage
			default:
				nameToken = NameTokens.getHomePage();
				break;
		}		
		return nameToken;
	}

	private void setActiveMenuItem(String name) {
		// Delegate to the MainMenu
		getView().getMainMenu().setActiveMenuItem(name);
	}
	
	private DialogBox createLoginDialogBox() { 
		// Create a dialog box and set the caption text
		final ExtendedDialogBox dialogBox = new ExtendedDialogBox();
		
		dialogBox.setGlassEnabled(true);
		dialogBox.setModal(true);
		dialogBox.setAnimationEnabled(true);
		dialogBox.setText("Log In ");
		
		// Create a table to layout the content
		final VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(5);
		dialogBox.setWidget(dialogContents);

		Anchor close = new Anchor("X");
		close.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				dialogBox.hide();
			}
		});
		close.setStyleName("logInClose");
		dialogContents.add(close);
		dialogContents.setCellHorizontalAlignment(close, HasHorizontalAlignment.ALIGN_RIGHT);
		
		final HTML credentialsFail = new HTML("*Authentification Failed (" + logInAttempt + ")");
		credentialsFail.setStyleName("credentialsFail", true);
		
		// Add some text to the top of the dialog
		HTML userName = new HTML("UserName");
		dialogContents.add(userName);
		dialogContents.setCellHorizontalAlignment(userName, HasHorizontalAlignment.ALIGN_LEFT);

		// Add an box to the dialog
		boxUserName.setStyleName("logInBox", true);
		dialogContents.add(boxUserName);
		dialogContents.setCellHorizontalAlignment(boxUserName, HasHorizontalAlignment.ALIGN_CENTER);

		// Add some text to the top of the dialog
		HTML password = new HTML("Password");
		dialogContents.add(password);
		dialogContents.setCellHorizontalAlignment(password, HasHorizontalAlignment.ALIGN_LEFT);

		// Add an box to the dialog
		final PasswordTextBox boxPassword = new PasswordTextBox();
		boxPassword.setStyleName("boxPassword", true);
		boxPassword.setStyleName("logInBox", true);
		dialogContents.add(boxPassword);
		dialogContents.setCellHorizontalAlignment(boxPassword, HasHorizontalAlignment.ALIGN_CENTER);

		// Add a confirm button at the bottom of the dialog
		confirmButton.setHTML("Ok");
		confirmButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				
				LoginRequest loginRequest = new LoginRequest();
				loginRequest.setUserName(boxUserName.getText());
				loginRequest.setPassword(boxPassword.getText());
				loginRequest.setSessionID(Cookies.getCookie("SessionID"));
				loginRequest.setCulture(locale.getCulture());
				loginRequest.setLanguage(locale.getLanguage());
				requestService.sendRequest(loginRequest, new AsyncCallback<AbstractResponse>() {

					@Override
					public void onSuccess(AbstractResponse result) {

						if (result.GetResponseType() == ResponseTypeEnum.LOGIN_RESPONSE){
							LoginResponse response = (LoginResponse) result;
							
							if (response.getLoginStatus() == LoginStatus.SUCCESS){
								// Login Sucessfull								
								String sessionID = response.getSessionID();
								
								// Remember 30 minutes
								Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 * 30);								
								// Set the sessionID cookie
								Cookies.setCookie("SessionID", sessionID, expiration);
								
								// User data
								UserCoreContent userCoreContent = response.getUserCoreContent();
								
								getView().getMainMenu().getLogInProfilUi().getLogInName().setText(
										userCoreContent.getFirstName() + " " + 
										userCoreContent.getLastName()
								);
								
								// Display the logged in profil Ui
								displayLoginProfilUi();
								boxUserName.setText("");
								boxPassword.setText("");
								dialogContents.remove(credentialsFail);
								logInAttempt = 1;
								dialogBox.hide();								
							} else  if (response.getLoginStatus() == LoginStatus.FAILURE){
								// Login not sucessfull, display error text in login dialog
								credentialsFail.setText("*Authentification Failed (" + logInAttempt + ")");
								dialogContents.add(credentialsFail);
								logInAttempt++;
							}else if(response.getLoginStatus() == LoginStatus.LOCKED) {
								// Login not sucessfull, display error text in login dialog
								credentialsFail.setText("*Account locked for 15 minutes, too many failed attempt to connect.");
								dialogContents.add(credentialsFail);
								logInAttempt++;
							}
						} else {
							// Wrong response type, hide box, don't display LogInProfilUi
							dialogBox.hide();
						}						
					}
					
					@Override
					public void onFailure(Throwable caught) {
						// On failure, hide box, don't display LogInProfilUi
						dialogBox.hide();						
					}
				});
			}
		});
		
		confirmButton.setStyleName("backButton", true);
		dialogContents.add(confirmButton);
		
		if (LocaleInfo.getCurrentLocale().isRTL()) {
			dialogContents.setCellHorizontalAlignment(confirmButton, HasHorizontalAlignment.ALIGN_LEFT);

		} else {
			dialogContents.setCellHorizontalAlignment(confirmButton, HasHorizontalAlignment.ALIGN_RIGHT);
		}
		

		// Return the dialog box
		return dialogBox;
	}
}
