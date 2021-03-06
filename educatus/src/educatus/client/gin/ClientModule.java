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
import educatus.client.presenter.ConfirmChangesPresenter;
import educatus.client.presenter.HomePresenter;
import educatus.client.presenter.ImageUploadPresenter;
import educatus.client.presenter.MainPagePresenter;
import educatus.client.presenter.ProfilPresenter;
import educatus.client.presenter.CategoryPresenter;
import educatus.client.presenter.SeminarHomePresenter;
import educatus.client.presenter.SeminaryEditPresenter;
import educatus.client.presenter.SeminaryListPresenter;
import educatus.client.presenter.SeminaryViewPresenter;
import educatus.client.view.ConfirmChangesView;
import educatus.client.view.HomeView;
import educatus.client.view.ImageUploadView;
import educatus.client.view.MainPageView;
import educatus.client.view.ProfilView;
import educatus.client.view.CategoryView;
import educatus.client.view.SeminarHomeView;
import educatus.client.view.SeminaryEditView;
import educatus.client.view.SeminaryListView;
import educatus.client.view.SeminaryViewView;
import educatus.client.view.TextEditView;
import educatus.client.presenter.TextEditPresenter;

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
    bindPresenter(SeminaryEditPresenter.class,
				SeminaryEditPresenter.MyView.class, SeminaryEditView.class,
				SeminaryEditPresenter.MyProxy.class);
    bindPresenter(SeminarHomePresenter.class, SeminarHomePresenter.MyView.class,
            SeminarHomeView.class, SeminarHomePresenter.MyProxy.class);
	bindPresenterWidget(CategoryPresenter.class,
			CategoryPresenter.MyView.class, CategoryView.class);
	bindPresenterWidget(SeminaryListPresenter.class,
			SeminaryListPresenter.MyView.class, SeminaryListView.class);
    bindPresenter(ProfilPresenter.class, ProfilPresenter.MyView.class,
			ProfilView.class, ProfilPresenter.MyProxy.class);
	bindPresenterWidget(ConfirmChangesPresenter.class,
			ConfirmChangesPresenter.MyView.class, ConfirmChangesView.class);
	bindPresenter(SeminaryViewPresenter.class,
			SeminaryViewPresenter.MyView.class, SeminaryViewView.class,
			SeminaryViewPresenter.MyProxy.class);  
	bindPresenterWidget(TextEditPresenter.class,
			TextEditPresenter.MyView.class,
			TextEditView.class);
	bindPresenterWidget(ImageUploadPresenter.class,
			ImageUploadPresenter.MyView.class,
			ImageUploadView.class);
}
}