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

import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import educatus.client.presenter.HomePresenter;
import educatus.client.presenter.MainPagePresenter;
import educatus.client.presenter.ProfilPresenter;
import educatus.client.presenter.SeminarHomePresenter;


/**
 * @author Christian Goudreau
 */
@GinModules({ClientModule.class})
public interface ClientGinjector extends Ginjector {
  EventBus getEventBus();

  AsyncProvider<HomePresenter> getHomePresenter();

  Provider<MainPagePresenter> getMainPagePresenter();

  AsyncProvider<ProfilPresenter> getProfilPresenter();  
  AsyncProvider<SeminarHomePresenter> getSeminarHomePresenter();

  PlaceManager getPlaceManager();



}