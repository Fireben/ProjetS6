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

package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.UListElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple menu that can be reused.
 * 
 * @author Christian Goudreau
 */
public class MainMenu extends Composite {
	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);
	@UiField
	UListElement mainMenuList;

	@UiField
	LogIn logInUi;
	@UiField
	LogInProfil logInProfilUi;
//	@UiField
//	InlineHyperlink mainMenuHomeButton;
//	@UiField
//	InlineHyperlink mainMenuSeminarsButton;
//	@UiField
//	InlineHyperlink mainMenuProfilButton;
//	@UiField
//	InlineHyperlink mainMenuEditSeminaryButton;

	public MainMenu() {
		initWidget(uiBinder.createAndBindUi(this));
	}

//	// Getter and Setter
//	public InlineHyperlink getMainMenuHomeButton() {
//		return mainMenuHomeButton;
//	}
//
//	public void setMainMenuHomeButton(InlineHyperlink mainMenuHomeButton) {
//		this.mainMenuHomeButton = mainMenuHomeButton;
//	}
//
//	public InlineHyperlink getMainMenuSeminarsButton() {
//		return mainMenuSeminarsButton;
//	}
//
//	public void setMainMenuSeminarsButton(InlineHyperlink mainMenuSeminarsButton) {
//		this.mainMenuSeminarsButton = mainMenuSeminarsButton;
//	}
//
//	public InlineHyperlink getMainMenuProfilButton() {
//		return mainMenuProfilButton;
//	}
//
//	public void setMainMenuProfilButton(InlineHyperlink mainMenuProfilButton) {
//		this.mainMenuProfilButton = mainMenuProfilButton;
//	}
//
//	public InlineHyperlink getMainMenuEditSeminaryButton() {
//		return mainMenuEditSeminaryButton;
//	}
//
//	public void setMainMenuEditSeminaryButton(InlineHyperlink mainMenuEditSeminaryButton) {
//		this.mainMenuEditSeminaryButton = mainMenuEditSeminaryButton;
//	}

	public LogIn getLogInUi() {
		return logInUi;
	}

	public void setLogInUi(LogIn logInUi) {
		this.logInUi = logInUi;
	}

	public LogInProfil getLogInProfilUi() {
		return logInProfilUi;
	}

	public void setLogInProfilUi(LogInProfil logInProfilUi) {
		this.logInProfilUi = logInProfilUi;
	}

	public UListElement getMainMenuList() {
		return mainMenuList;
	}

	public void appendMainMenuItem(String itemName, String targetHistoryToken) {

		HTMLPanel hyperLinkPanel = new HTMLPanel("li", "");
		InlineHyperlink hyperlink = new InlineHyperlink(itemName, targetHistoryToken);
		hyperLinkPanel.add(hyperlink);
		mainMenuList.insertAfter(hyperLinkPanel.getElement(), null);
	}
	
	public void setActive(String activeNameToken){
		
	}

	public void clearMainMenuList() {
		while (mainMenuList.getChildCount() > 0) {
			com.google.gwt.dom.client.Node lastChild = mainMenuList.getLastChild();
			if (lastChild != null){
				mainMenuList.removeChild(lastChild);				
			}
		}
	}
}