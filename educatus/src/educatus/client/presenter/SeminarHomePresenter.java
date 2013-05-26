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

package educatus.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;

/**
 * @author Nicolas Michaud
 */
public class SeminarHomePresenter extends Presenter<SeminarHomePresenter.MyView, SeminarHomePresenter.MyProxy> {
    /**
     * {@link SeminarHomePresenter}'s proxy.
     */
    @ProxyCodeSplit
    @NameToken(NameTokens.seminarHomePage)
    public interface MyProxy extends ProxyPlace<SeminarHomePresenter> {
    }
    
    private int state = 0;
    
    public static final Object SLOT_content = new Object();
    
	@Inject
	SeminarCategoryPresenter seminarCategoryPresenter;
	
	@Inject
	SeminaryListPresenter seminaryListPresenter;

    /**
     * {@link SeminarHomePresenter}'s view.
     */
    public interface MyView extends View {	  
    }

    @Inject
    public SeminarHomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
      super(eventBus, view, proxy);
    }

  	@Override
	protected void revealInParent() {
	  RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
  
	@Override
	protected void onReset() {
	  super.onReset();    
	  state = 0;
	  /*
	  setInSlot(SLOT_content, seminarCategoryPresenter);
	  seminarCategoryPresenter.setAndAnimateCategoryPanel(state, categoryClickHandler);
	  */
	  setInSlot(SLOT_content, seminaryListPresenter);
	  seminaryListPresenter.initializeColumns();
	}
	
	private ClickHandler categoryClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			changeCategoryPanel();
		}
	};
	
	private void changeCategoryPanel() {
		state++;
		seminarCategoryPresenter.setAndAnimateCategoryPanel(state, categoryClickHandler);
	}
}