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

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

public class DropDown extends Composite implements ClickHandler {
	interface DropDownUiBinder extends UiBinder<Widget, DropDown> {
	}

	private ClickHandler adminButtonHandler;

	private Image img;
	private PushButton userImage;
//	private String adminButtonText = "Admin";
	private String userModeImgSrc = "images/user_login_user.png";

	public DropDown() {

		img = new Image(userModeImgSrc);
		
		img.setPixelSize(60, 32);

		userImage = new PushButton(img);

		userImage.addClickHandler(this);

		userImage.removeStyleName("gwt-PushButton");

		HorizontalPanel panel = new HorizontalPanel();
		panel.setStyleName("logInProfilImg", true);
		panel.add(userImage);

		// All composites must call initWidget() in their constructors.
		initWidget(panel);
		// initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void onClick(ClickEvent event) {
		if (adminButtonHandler != null) {
			adminButtonHandler.onClick(event);					
		}
		
		/*TODO Add a dialog box if more options are avaible for a box */
//		final DialogBox dialogBox = createOptionDropDown();
//		dialogBox.setGlassEnabled(false);
//		//dialogBox.setStyleName("logInProfilDialogBox");
//		dialogBox.setModal(false);
//		dialogBox.setAnimationEnabled(true);
//		dialogBox.setAutoHideEnabled(true);
//		dialogBox.setPopupPositionAndShow(new PositionCallback() {
//
//			@Override
//			public void setPosition(int offsetWidth, int offsetHeight) {
//				dialogBox.showRelativeTo(userImage);
//			}
//		});
//		// dialogBox.setPopupPosition(getAbsoluteLeft()-300,
//		// getAbsoluteTop()+50);
//		dialogBox.show();
	}

	/*TODO Add a dialog box if more options are avaible for a box */

//	private DialogBox createOptionDropDown() {
//		// Create a dialog box and set the caption text
//		final DialogBox dialogBox = new DialogBox();
//		// dialogBox.setText("Log In ");
//
//		// Create a table to layout the content
//		VerticalPanel dialogContents = new VerticalPanel();
//		dialogContents.setSpacing(5);
//		dialogBox.setWidget(dialogContents);
//
//		// Add a admin button at the bottom of the dialog
//		// HACK, refactorer le dropdown, on expose l'admin button pour changer le texte à partir du MainPagePresenter
//		Button adminButton = new Button(adminButtonText, new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				if (adminButtonHandler != null) {
//					adminButtonHandler.onClick(event);					
//				}
//				dialogBox.hide();
//			}
//		});
//		adminButton.setStyleName("backButton", true);
//		dialogContents.add(adminButton);
//		dialogContents.setCellHorizontalAlignment(adminButton,
//				HasHorizontalAlignment.ALIGN_RIGHT);
//
//		// Return the dialog box
//		return dialogBox;
//	}

	public ClickHandler getAdminButtonHandler() {
		return adminButtonHandler;
	}

	public void setAdminButtonHandler(ClickHandler adminButtonHandler) {
		this.adminButtonHandler = adminButtonHandler;
	}

	public void setUserModeImg(String imgSrc) {
		
		img.setUrl(imgSrc);
	}
	
//	public void setAdminButtonText(String text) {
//		adminButtonText = text;
//	}
}