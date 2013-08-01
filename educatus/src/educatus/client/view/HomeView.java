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
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.NameTokens;
import educatus.client.presenter.HomePresenter.MyView;

/**
 * @author Christian Goudreau
 */
public class HomeView extends ViewImpl implements MyView {
  interface HomeViewUiBinder extends UiBinder<Widget, HomeView> {
  }

	private static HomeViewUiBinder uiBinder = GWT
			.create(HomeViewUiBinder.class);

	private final Widget widget;
	@UiField Element homePageBulbImg;
	@UiField Element homePageSecondTitle;
	@UiField Element homePageFirstDescription;
	@UiField Element homePageFirstDescription2;
	@UiField Element homePageFirstDescription3;
	
	@UiField Element homePageFirstSectionTitle;
	@UiField Element homePageFirstSectionText;
	@UiField Element homePageFirstSectionImg;
	@UiField Element homePageFirstSectionDescription;
	@UiField Element homePageFirstSectionLink;
	
	@UiField Element homePageSecondSectionTitle;
	@UiField Element homePageSecondSectionText;
	@UiField Element homePageSecondSectionImg;
	@UiField Element homePageSecondSectionDescription;
	@UiField Element homePageSecondSectionLink;
	
	@UiField Element homePageThirdSectionTitle;
	@UiField Element homePageThirdSectionText;
	@UiField Element homePageThirdSectionImg;
	@UiField Element homePageThirdSectionDescription;
	
	@UiField Element homePageFourthSectionTitle;
	@UiField Element homePageFourthSectionText;
	@UiField Element homePageFourthSectionImg;
	@UiField Element homePageFourthSectionDescription;

	public HomeView()
	{
		widget = uiBinder.createAndBindUi(this);
		homePageFirstSectionLink.setAttribute("href", "#"+NameTokens.seminarHomePage);
		homePageSecondSectionLink.setAttribute("href", "#"+NameTokens.exerciceHomePage);
	}

	public Widget asWidget()
	{
		return widget;
	}
	
	// Getter only
	public Element getHomePageBulbImg() {
		return homePageBulbImg;
	}

	public Element getHomePageSecondTitle() {
		return homePageSecondTitle;
	}

	public Element getHomePageFirstDescription() {
		return homePageFirstDescription;
	}
	
	public Element getHomePageFirstDescription2() {
		return homePageFirstDescription2;
	}

	public Element getHomePageFirstDescription3() {
		return homePageFirstDescription3;
	}

	public Element getHomePageFirstSectionTitle() {
		return homePageFirstSectionTitle;
	}

	public Element getHomePageFirstSectionText() {
		return homePageFirstSectionText;
	}

	public Element getHomePageFirstSectionImg() {
		return homePageFirstSectionImg;
	}

	public Element getHomePageFirstSectionDescription() {
		return homePageFirstSectionDescription;
	}

	public Element getHomePageFirstSectionLink() {
		return homePageFirstSectionLink;
	}

	public Element getHomePageSecondSectionTitle() {
		return homePageSecondSectionTitle;
	}

	public Element getHomePageSecondSectionText() {
		return homePageSecondSectionText;
	}

	public Element getHomePageSecondSectionImg() {
		return homePageSecondSectionImg;
	}

	public Element getHomePageSecondSectionDescription() {
		return homePageSecondSectionDescription;
	}

	public Element getHomePageSecondSectionLink() {
		return homePageSecondSectionLink;
	}

	public Element getHomePageThirdSectionTitle() {
		return homePageThirdSectionTitle;
	}

	public Element getHomePageThirdSectionText() {
		return homePageThirdSectionText;
	}

	public Element getHomePageThirdSectionImg() {
		return homePageThirdSectionImg;
	}

	public Element getHomePageThirdSectionDescription() {
		return homePageThirdSectionDescription;
	}

	public Element getHomePageFourthSectionTitle() {
		return homePageFourthSectionTitle;
	}

	public Element getHomePageFourthSectionText() {
		return homePageFourthSectionText;
	}

	public Element getHomePageFourthSectionImg() {
		return homePageFourthSectionImg;
	}

	public Element getHomePageFourthSectionDescription() {
		return homePageFourthSectionDescription;
	}
}