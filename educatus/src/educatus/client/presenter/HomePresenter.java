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
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;
import educatus.client.services.RequestService;
import educatus.client.services.RequestServiceAsync;
import educatus.shared.businesslogic.dto.HomePageContent;
import educatus.shared.services.requestservice.AbstractResponse;
import educatus.shared.services.requestservice.ResponseTypeEnum;
import educatus.shared.services.requestservice.request.HomePageContentRequest;
import educatus.shared.services.requestservice.response.HomePageContentResponse;

/**
 * @author Christian Goudreau
 */
public class HomePresenter extends
		Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {
	/**
	 * {@link HomePresenter}'s proxy.
	 */
	@ProxyCodeSplit
	@NameToken(NameTokens.homePage)
	public interface MyProxy extends ProxyPlace<HomePresenter> {
	}

	// Create a remote service proxy to talk to the server-side service.
	private final RequestServiceAsync requestService = GWT
			.create(RequestService.class);

	/**
	 * {@link HomePresenter}'s view.
	 */
	public interface MyView extends View {
		public Element getHomePageBulbImg();

		public Element getHomePageSecondTitle();

		public Element getHomePageFirstDescription();

		public Element getHomePageFirstDescription2();

		public Element getHomePageFirstDescription3();

		public Element getHomePageFirstSectionTitle();

		public Element getHomePageFirstSectionText();

		public Element getHomePageFirstSectionImg();

		public Element getHomePageFirstSectionDescription();

		public Element getHomePageFirstSectionLink();

		public Element getHomePageSecondSectionTitle();

		public Element getHomePageSecondSectionText();

		public Element getHomePageSecondSectionImg();

		public Element getHomePageSecondSectionDescription();

		public Element getHomePageSecondSectionLink();

		public Element getHomePageThirdSectionTitle();

		public Element getHomePageThirdSectionText();

		public Element getHomePageThirdSectionImg();

		public Element getHomePageThirdSectionDescription();

		public Element getHomePageThirdSectionLink();

		public Element getHomePageFourthSectionTitle();

		public Element getHomePageFourthSectionText();

		public Element getHomePageFourthSectionImg();

		public Element getHomePageFourthSectionDescription();
	}

	@Inject
	public HomePresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
				this);
	}

	@Override
	protected void onBind() {
		super.onBind();
		
		HomePageContentRequest request = new HomePageContentRequest();
		request.setCulture("CA");
		request.setLanguage("en");
		requestService.sendRequest(request , new AsyncCallback<AbstractResponse>() {
			
			@Override
			public void onSuccess(AbstractResponse result) {
				if (result.GetResponseType() == ResponseTypeEnum.HOME_PAGE_CONTENT_RESPONSE) {
					HomePageContentResponse response = (HomePageContentResponse) result;
					fillPageWithContent(response.getHomeContent());
				}				
			}
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	protected void onReset() {
		super.onReset();
		PageChangingEvent.fire(this, NameTokens.getHomePage());
		// getView().getHomePageTitle().setInnerText("Testing");
		// getView().getHomePageFirstDescription().setInnerText("El Test");
	}
	
	private void fillPageWithContent(HomePageContent content)
	{
		// Welcome 
		getView().getHomePageSecondTitle().setInnerText(content.getWelcomeTitle());
		getView().getHomePageBulbImg().setAttribute("src", content.getWelcomeImage());
		getView().getHomePageFirstDescription().setInnerText(content.getWelcomeDescription1());
		getView().getHomePageFirstDescription2().setInnerText(content.getWelcomeDescription2());
		getView().getHomePageFirstDescription3().setInnerText(content.getWelcomeDescription3());
		
		// Seminary section
		getView().getHomePageFirstSectionTitle().setInnerText(content.getSeminarsSection().getSectionTitle());
		getView().getHomePageFirstSectionText().setInnerText(content.getSeminarsSection().getSectionText());
		getView().getHomePageFirstSectionDescription().setInnerText(content.getSeminarsSection().getSectionDescription());
		getView().getHomePageFirstSectionLink().setInnerText(content.getSeminarsSection().getSectionLinkText());
		getView().getHomePageFirstSectionImg().setAttribute("src", content.getSeminarsSection().getSectionImg());

		// Problems section
		getView().getHomePageSecondSectionTitle().setInnerText(content.getProblemsSection().getSectionTitle());
		getView().getHomePageSecondSectionText().setInnerText(content.getProblemsSection().getSectionText());
		getView().getHomePageSecondSectionDescription().setInnerText(content.getProblemsSection().getSectionDescription());
		getView().getHomePageSecondSectionLink().setInnerText(content.getProblemsSection().getSectionLinkText());
		getView().getHomePageSecondSectionImg().setAttribute("src", content.getProblemsSection().getSectionImg());
		
		// Statistics section
		getView().getHomePageThirdSectionTitle().setInnerText(content.getStatisticsSection().getSectionTitle());
		getView().getHomePageThirdSectionText().setInnerText(content.getStatisticsSection().getSectionText());
		getView().getHomePageThirdSectionDescription().setInnerText(content.getStatisticsSection().getSectionDescription());
		getView().getHomePageThirdSectionLink().setInnerText(content.getStatisticsSection().getSectionLinkText());
		getView().getHomePageThirdSectionImg().setAttribute("src", content.getStatisticsSection().getSectionImg());

		// Forums section
		getView().getHomePageFourthSectionTitle().setInnerText(content.getForumsSection().getSectionTitle());
		getView().getHomePageFourthSectionText().setInnerText(content.getForumsSection().getSectionText());
		getView().getHomePageFourthSectionDescription().setInnerText(content.getForumsSection().getSectionDescription());
		getView().getHomePageFourthSectionImg().setAttribute("src", content.getForumsSection().getSectionImg());
		
	}
}