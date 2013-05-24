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

import com.google.gwt.dom.client.Element;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;

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

  /**
   * {@link HomePresenter}'s view.
   */
  public interface MyView extends View {
		public Element getHomePageTitle();
		public Element getHomePageBulbImg();
		public Element getHomePageSecondTitle();
		public Element getHomePageFirstDescription();
		public Element getHomePageFirstDescription2();
		public Element getHomePageFirstDescription3();

		public Element getHomePageButton();
		
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
  }
 
  @Override
	protected void onReset() {
		super.onReset();
		//getView().getHomePageTitle().setInnerText("Testing");
		//getView().getHomePageFirstDescription().setInnerText("El Test");
	}
}