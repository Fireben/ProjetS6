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
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;

/**
 * A simple menu that can be reused.
 * 
 * @author
 */
public class Footer extends Composite {
	interface FooterUiBinder extends UiBinder<Widget, Footer> {
	}

	private static FooterUiBinder uiBinder = GWT.create(FooterUiBinder.class);

	@UiField
	PushButton frenchButton;
	@UiField
	PushButton englishButton;

	public Footer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public PushButton getFrenchButton() {
		return frenchButton;
	}

	public PushButton getEnglishButton() {
		return englishButton;
	}
}