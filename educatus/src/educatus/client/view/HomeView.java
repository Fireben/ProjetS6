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
	@UiField Element homePageTitle;
	@UiField Element homePageBulbImg;
	@UiField Element homePageSecondTitle;
	@UiField Element homePageFirstDescription;
	@UiField Element homePageFirstDescription2;
	@UiField Element homePageFirstDescription3;
	
	@UiField Element homePageButton;
	
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
	@UiField Element homePageThirdSectionLink;
	
	@UiField Element homePageFourthSectionTitle;
	@UiField Element homePageFourthSectionText;
	@UiField Element homePageFourthSectionImg;
	@UiField Element homePageFourthSectionDescription;

	public HomeView()
	{
		widget = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget()
	{
		return widget;
	}
	
	//Getter and Setter
	public Element getHomePageTitle() {
		return homePageTitle;
	}

	public void setHomePageTitle(Element homePageTitle) {
		this.homePageTitle = homePageTitle;
	}

	public Element getHomePageBulbImg() {
		return homePageBulbImg;
	}

	public void setHomePageBulbImg(Element homePageBulbImg) {
		this.homePageBulbImg = homePageBulbImg;
	}

	public Element getHomePageSecondTitle() {
		return homePageSecondTitle;
	}

	public void setHomePageSecondTitle(Element homePageSecondTitle) {
		this.homePageSecondTitle = homePageSecondTitle;
	}

	public Element getHomePageFirstDescription() {
		return homePageFirstDescription;
	}

	public void setHomePageFirstDescription(Element homePageFirstDescription) {
		this.homePageFirstDescription = homePageFirstDescription;
	}
	
	public Element getHomePageFirstDescription2() {
		return homePageFirstDescription2;
	}

	public void setHomePageFirstDescription2(Element homePageFirstDescription2) {
		this.homePageFirstDescription2 = homePageFirstDescription2;
	}

	public Element getHomePageFirstDescription3() {
		return homePageFirstDescription3;
	}

	public void setHomePageFirstDescription3(Element homePageFirstDescription3) {
		this.homePageFirstDescription3 = homePageFirstDescription3;
	}

	public Element getHomePageButton() {
		return homePageButton;
	}

	public void setHomePageButton(Element homePageButton) {
		this.homePageButton = homePageButton;
	}

	public Element getHomePageFirstSectionTitle() {
		return homePageFirstSectionTitle;
	}

	public void setHomePageFirstSectionTitle(Element homePageFirstSectionTitle) {
		this.homePageFirstSectionTitle = homePageFirstSectionTitle;
	}

	public Element getHomePageFirstSectionText() {
		return homePageFirstSectionText;
	}

	public void setHomePageFirstSectionText(Element homePageFirstSectionText) {
		this.homePageFirstSectionText = homePageFirstSectionText;
	}

	public Element getHomePageFirstSectionImg() {
		return homePageFirstSectionImg;
	}

	public void setHomePageFirstSectionImg(Element homePageFirstSectionImg) {
		this.homePageFirstSectionImg = homePageFirstSectionImg;
	}

	public Element getHomePageFirstSectionDescription() {
		return homePageFirstSectionDescription;
	}

	public void setHomePageFirstSectionDescription(
			Element homePageFirstSectionDescription) {
		this.homePageFirstSectionDescription = homePageFirstSectionDescription;
	}

	public Element getHomePageFirstSectionLink() {
		return homePageFirstSectionLink;
	}

	public void setHomePageFirstSectionLink(Element homePageFirstSectionLink) {
		this.homePageFirstSectionLink = homePageFirstSectionLink;
	}

	public Element getHomePageSecondSectionTitle() {
		return homePageSecondSectionTitle;
	}

	public void setHomePageSecondSectionTitle(Element homePageSecondSectionTitle) {
		this.homePageSecondSectionTitle = homePageSecondSectionTitle;
	}

	public Element getHomePageSecondSectionText() {
		return homePageSecondSectionText;
	}

	public void setHomePageSecondSectionText(Element homePageSecondSectionText) {
		this.homePageSecondSectionText = homePageSecondSectionText;
	}

	public Element getHomePageSecondSectionImg() {
		return homePageSecondSectionImg;
	}

	public void setHomePageSecondSectionImg(Element homePageSecondSectionImg) {
		this.homePageSecondSectionImg = homePageSecondSectionImg;
	}

	public Element getHomePageSecondSectionDescription() {
		return homePageSecondSectionDescription;
	}

	public void setHomePageSecondSectionDescription(
			Element homePageSecondSectionDescription) {
		this.homePageSecondSectionDescription = homePageSecondSectionDescription;
	}

	public Element getHomePageSecondSectionLink() {
		return homePageSecondSectionLink;
	}

	public void setHomePageSecondSectionLink(Element homePageSecondSectionLink) {
		this.homePageSecondSectionLink = homePageSecondSectionLink;
	}

	public Element getHomePageThirdSectionTitle() {
		return homePageThirdSectionTitle;
	}

	public void setHomePageThirdSectionTitle(Element homePageThirdSectionTitle) {
		this.homePageThirdSectionTitle = homePageThirdSectionTitle;
	}

	public Element getHomePageThirdSectionText() {
		return homePageThirdSectionText;
	}

	public void setHomePageThirdSectionText(Element homePageThirdSectionText) {
		this.homePageThirdSectionText = homePageThirdSectionText;
	}

	public Element getHomePageThirdSectionImg() {
		return homePageThirdSectionImg;
	}

	public void setHomePageThirdSectionImg(Element homePageThirdSectionImg) {
		this.homePageThirdSectionImg = homePageThirdSectionImg;
	}

	public Element getHomePageThirdSectionDescription() {
		return homePageThirdSectionDescription;
	}

	public void setHomePageThirdSectionDescription(
			Element homePageThirdSectionDescription) {
		this.homePageThirdSectionDescription = homePageThirdSectionDescription;
	}

	public Element getHomePageThirdSectionLink() {
		return homePageThirdSectionLink;
	}

	public void setHomePageThirdSectionLink(Element homePageThirdSectionLink) {
		this.homePageThirdSectionLink = homePageThirdSectionLink;
	}

	public Element getHomePageFourthSectionTitle() {
		return homePageFourthSectionTitle;
	}

	public void setHomePageFourthSectionTitle(Element homePageFourthSectionTitle) {
		this.homePageFourthSectionTitle = homePageFourthSectionTitle;
	}

	public Element getHomePageFourthSectionText() {
		return homePageFourthSectionText;
	}

	public void setHomePageFourthSectionText(Element homePageFourthSectionText) {
		this.homePageFourthSectionText = homePageFourthSectionText;
	}

	public Element getHomePageFourthSectionImg() {
		return homePageFourthSectionImg;
	}

	public void setHomePageFourthSectionImg(Element homePageFourthSectionImg) {
		this.homePageFourthSectionImg = homePageFourthSectionImg;
	}

	public Element getHomePageFourthSectionDescription() {
		return homePageFourthSectionDescription;
	}

	public void setHomePageFourthSectionDescription(
			Element homePageFourthSectionDescription) {
		this.homePageFourthSectionDescription = homePageFourthSectionDescription;
	}

}