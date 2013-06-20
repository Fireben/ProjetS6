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

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.i18n.client.LocaleInfo;
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
import educatus.shared.dto.MainPageContent;
import educatus.shared.services.RequestService;
import educatus.shared.services.RequestServiceAsync;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.MainPageContentRequest;
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

		public FlowPanel getHeaderPanel();
		
		public MainMenu getMenuPanel();
		
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
			
			//TODO Modify behavior 
			ResetPresentersEvent.fire(MainPagePresenter.this);
		}
	}
	
	/**
	 * Use this in leaf presenters, inside their {@link #revealInParent} method.
	 */
	@ContentSlot
	public static final Type<RevealContentHandler<?>> TYPE_SetMainContent = new Type<RevealContentHandler<?>>();

	@Inject
	public MainPagePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
		super(eventBus, view, proxy);
	}
	
	@Inject
	private EducatusLocale locale;
	
	private MainPageContentRequest request = new MainPageContentRequest();

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
		//getView().getMenuPanel().getLogInUi().getLogInLink().setInnerText("Changed");
		getView().getMenuPanel().getLogInUi().getLogInLink().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				 final DialogBox dialogBox = createDialogBox();
				    dialogBox.setGlassEnabled(true);
				    dialogBox.setModal(true);
				    dialogBox.setAnimationEnabled(true);
				    dialogBox.center();
		            dialogBox.show();
			}
		});
		
		
		getView().getHeaderPanel().add(getView().getMenuPanel());
		
		getView().getFooterPanel().getEnglishButton().addClickHandler(new TranslateClickHandler("CA", "en"));
		getView().getFooterPanel().getFrenchButton().addClickHandler(new TranslateClickHandler("CA", "fr"));
		
		
		addRegisteredHandler(PageChangingEvent.getType(), new PageChangeHandler() {
			@Override
			public void onPageChange(PageChangingEvent event) {
				setActiveMenuItem(event.getMessage());
			}
		});
	}
	
	@Override 
	protected void onReset() {
		super.onReset();
		
		if(request.getCulture() != locale.getCulture() || request.getLanguage() != locale.getLanguage())
		{
			request.setCulture(locale.getCulture());
			request.setLanguage(locale.getLanguage());
			requestService.sendRequest(request, new AsyncCallback<AbstractResponse>() {

				@Override
				public void onSuccess(AbstractResponse result) {
					if (result.GetResponseType() == ResponseTypeEnum.MAIN_PAGE_CONTENT_RESPONSE) {
						MainPageContentResponse response = (MainPageContentResponse) result;
						MainPageContent content = response.getMainPageContent();
						
						// Move this to dedicated method, as in the HomePresenter
						getView().getMenuPanel().getMainMenuHomeButton().getElement().setInnerText(content.getMainMenuContent().getHomeItem().getName());
						getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setInnerText(content.getMainMenuContent().getSeminaryItem().getName());
						getView().getMenuPanel().getMainMenuProfilButton().getElement().setInnerText(content.getMainMenuContent().getProfilItem().getName());
						getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setInnerText(content.getMainMenuContent().getViewerItem().getName());
						getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setInnerText(content.getMainMenuContent().getEditorItem().getName());
					}
				}

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}
			});
		}
	}

	private void setActiveMenuItem(String name) {
		if (name == NameTokens.getHomePage()) {
			getView().getMenuPanel().getMainMenuHomeButton().getElement().setClassName("active");
			getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuProfilButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
		} else if (name == NameTokens.getProfil()) {
			getView().getMenuPanel().getMainMenuHomeButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuProfilButton().getElement().setClassName("active");
			getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
		} else if (name == NameTokens.getSeminarHomePage()) {
			getView().getMenuPanel().getMainMenuHomeButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setClassName("active");
			getView().getMenuPanel().getMainMenuProfilButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
		} else if (name == NameTokens.getViewSeminary()) {
			getView().getMenuPanel().getMainMenuHomeButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuProfilButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setClassName("active");
			getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
		} else if (name == NameTokens.getSeminaryEdit()) {
			getView().getMenuPanel().getMainMenuHomeButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuSeminarsButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuProfilButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuViewSeminaryButton().getElement().setClassName("gwt-InlineHyperlink");
			getView().getMenuPanel().getMainMenuEditSeminaryButton().getElement().setClassName("active");
		}
	}
	
	/**
	   * Create the dialog box for this example.
	   *
	   * @return the new dialog box
	   */
	  private DialogBox createDialogBox() {
	    // Create a dialog box and set the caption text
	    final DialogBox dialogBox = new DialogBox();
	    dialogBox.setText("Log In ");

	    // Create a table to layout the content
	    VerticalPanel dialogContents = new VerticalPanel();
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
	    dialogContents.setCellHorizontalAlignment(
	    		close, HasHorizontalAlignment.ALIGN_RIGHT);
	    
	    // Add some text to the top of the dialog
	    HTML userName = new HTML("UserName");
	    dialogContents.add(userName);
	    dialogContents.setCellHorizontalAlignment(
	    	userName, HasHorizontalAlignment.ALIGN_LEFT);

	    // Add an box to the dialog
	    TextBox boxUserName = new TextBox();
	    boxUserName.setStyleName("logInBox", true);
	    dialogContents.add(boxUserName);
	    dialogContents.setCellHorizontalAlignment(
	    		boxUserName, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    // Add some text to the top of the dialog
	    HTML password = new HTML("Password");
	    dialogContents.add(password);
	    dialogContents.setCellHorizontalAlignment(
	    		password, HasHorizontalAlignment.ALIGN_LEFT);

	    // Add an box to the dialog
	    PasswordTextBox boxPassword = new PasswordTextBox();
	    boxPassword.setStyleName("boxPassword", true);
	    boxPassword.setStyleName("logInBox", true);
	    dialogContents.add(boxPassword);
	    dialogContents.setCellHorizontalAlignment(
	    		boxPassword, HasHorizontalAlignment.ALIGN_CENTER);

	    // Add a close button at the bottom of the dialog
	    Button closeButton = new Button(
	        "Ok", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	            dialogBox.hide();
	          }
	        });
	    closeButton.setStyleName("backButton", true);
	    dialogContents.add(closeButton);
	    if (LocaleInfo.getCurrentLocale().isRTL()) {
	      dialogContents.setCellHorizontalAlignment(
	          closeButton, HasHorizontalAlignment.ALIGN_LEFT);

	    } else {
	      dialogContents.setCellHorizontalAlignment(
	          closeButton, HasHorizontalAlignment.ALIGN_RIGHT);
	    }

	    // Return the dialog box
	    return dialogBox;
	  }
}
