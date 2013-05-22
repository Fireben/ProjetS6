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

package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.HomePresenter.MyView;

/**
 * @author Christian Goudreau
 */
public class HomeView extends ViewImpl implements MyView {
  interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
  }
  @UiField Image newsPaper;

  private static HomeViewUiBinder uiBinder = GWT.create(HomeViewUiBinder.class);

  private final Widget widget;

  public HomeView() {
    widget = uiBinder.createAndBindUi(this);
  }

  public Widget asWidget() {
    return widget;
  }

public Image getNewspaperImage() {
	 return newsPaper;
}
}