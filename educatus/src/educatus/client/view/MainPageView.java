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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.MainPagePresenter;
import educatus.client.presenter.MainPagePresenter.MyView;
import educatus.client.ui.Footer;
import educatus.client.ui.MainMenu;

/**
 * This is the top-level view of the application. Every time another presenter
 * wants to reveal itself, {@link MainPageView} will add its content of the
 * target inside the {@code mainContantPanel}.
 * 
 * @author Christian Goudreau
 */
public class MainPageView extends ViewImpl implements MyView {
	interface MainPageViewUiBinder extends UiBinder<Widget, MainPageView> {
	}

	private static MainPageViewUiBinder uiBinder = GWT.create(MainPageViewUiBinder.class);
	
	private final Widget widget;

	@UiField
	FlowPanel headerPanel;

	@UiField
	FlowPanel mainContentPanel;

	private MainMenu menuPanel = new MainMenu();

	@UiField
	Footer footerPanel;

	public MainPageView() {
		widget = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return widget;
	}

	public void setInSlot(Object slot, Widget content) {
		if (slot == MainPagePresenter.TYPE_SetMainContent) {
			setMainContent(content);
		} else {
			super.setInSlot(slot, content);
		}
	}

	private void setMainContent(Widget content) {
		mainContentPanel.clear();

		if (content != null) {
			mainContentPanel.add(content);
		}
	}

	public void showLoading(boolean visibile) {
	}

	public FlowPanel getMainContentPanel() {
		return mainContentPanel;
	}

	public void setMainContentPanel(FlowPanel mainContentPanel) {
		this.mainContentPanel = mainContentPanel;
	}

	public MainMenu getMenuPanel() {
		return menuPanel;
	}

	public void setMenuPanel(MainMenu menuPanel) {
		this.menuPanel = menuPanel;
	}

	public Footer getFooterPanel() {
		return footerPanel;
	}

	public void setFooterPanel(Footer footerPanel) {
		this.footerPanel = footerPanel;
	}

	public FlowPanel getHeaderPanel() {
		return headerPanel;
	}

	public void setHeaderPanel(FlowPanel headerPanel) {
		this.headerPanel = headerPanel;
	}
}