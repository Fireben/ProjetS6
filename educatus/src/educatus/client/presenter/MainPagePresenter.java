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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.ui.FlowPanel;
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

		public MainMenu getMenuPanel();

		public Footer getFooterPanel();

		void showLoading(boolean visibile);
	}

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
		
		getView().getFooterPanel().getEnglishButton().addClickHandler(new TranslateClickHandler("CA", "en"));
		getView().getFooterPanel().getFrenchButton().addClickHandler(new TranslateClickHandler("CA", "fr"));
		
		
		addRegisteredHandler(PageChangingEvent.getType(), new PageChangeHandler() {
			@Override
			public void onPageChange(PageChangingEvent event) {
				setActiveMenuItem(event.getMessage());
			}
		});
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
}
