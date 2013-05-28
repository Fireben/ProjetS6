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

import java.util.ArrayList;
import java.util.List;

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
import educatus.client.animation.FadeAnimation;
import educatus.client.events.PageChangingEvent;
import educatus.client.ui.dataGrids.Seminary;

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
	CategoryPresenter seminarCategoryPresenter;
	
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
	  PageChangingEvent.fire(this, NameTokens.getSeminarHomePage());
	}
	
	@Override
	protected void onReveal() {
		state = 0;	  
		setInSlot(SLOT_content, seminarCategoryPresenter);
		seminarCategoryPresenter.setAndAnimateCategoryPanel(state, categoryClickHandler);			
	}
	
	private ClickHandler categoryClickHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			changeState();
		}
	};
	
	private void changeCategoryPanel() {		
		seminarCategoryPresenter.setAndAnimateCategoryPanel(state, categoryClickHandler);
	}
	
	private void changeState() {
		state++;
		if(state < 2) {
			changeCategoryPanel();
		}
		else {
			setSeminaryList();
		}
	}
	
	private void setSeminaryList() {
		setInSlot(SLOT_content, seminaryListPresenter);
		List<Seminary> seminaries = new ArrayList<Seminary>();;
		for(int i=1;i<=30;i++) {
			seminaries.add(new Seminary(i, "Sauce", "Comment faire de la sauce ?", null, null, null));
		}	
		seminaryListPresenter.setData(seminaries);
		FadeAnimation animation;
		animation = new FadeAnimation(seminaryListPresenter.getView().getDataGrid(), FadeAnimation.MIN_OPACITY,
										FadeAnimation.MAX_OPACITY, FadeAnimation.VERY_LONG);
		animation.start();
	}
}