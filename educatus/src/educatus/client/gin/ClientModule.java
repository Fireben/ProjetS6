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

package educatus.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

import educatus.client.EducatusPlaceManager;
import educatus.client.NameTokens;
import educatus.client.presenter.HomePresenter;
import educatus.client.presenter.MainPagePresenter;
import educatus.client.view.HomeView;
import educatus.client.view.MainPageView;
/**
 * @author Christian Goudreau
 */
public class ClientModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    // Default implementation of standard resources
    install(new DefaultModule(EducatusPlaceManager.class));

    // Constants
    bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.homePage);

    // Presenters
    bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
        MainPageView.class, MainPagePresenter.MyProxy.class);
    bindPresenter(HomePresenter.class, HomePresenter.MyView.class,
        HomeView.class, HomePresenter.MyProxy.class);
  }
}