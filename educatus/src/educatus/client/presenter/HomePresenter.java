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

import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import educatus.client.NameTokens;
import educatus.client.presenter.MainPagePresenter;

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
	  Image  getNewspaperImage();
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
    registerHandler(getView().getNewspaperImage().addClickHandler(
        new ClickHandler() {
          public void onClick(ClickEvent event) {
        	  getView().getNewspaperImage().setVisible(false);
          }
        }));
  }
}