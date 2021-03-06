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

package educatus.client;

import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;

import com.google.web.bindery.event.shared.EventBus;
import com.google.inject.Inject;

import educatus.client.gin.DefaultPlace;

/**
 * @author Christian Goudreau
 */
public class EducatusPlaceManager extends PlaceManagerImpl {
  private final PlaceRequest defaultPlaceRequest;

@Inject
  public EducatusPlaceManager(final EventBus eventBus,
      final TokenFormatter tokenFormatter, @DefaultPlace String defaultNameToken) {
    super(eventBus, tokenFormatter);

    this.defaultPlaceRequest = new PlaceRequest(NameTokens.getHomePage());
  }

  public void revealDefaultPlace() {
    revealPlace(defaultPlaceRequest);
  }
}