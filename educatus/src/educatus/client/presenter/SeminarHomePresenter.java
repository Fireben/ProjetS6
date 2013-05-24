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

import java.util.Iterator;

import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.animation.ListFadeAnimation;
import educatus.client.presenter.MainPagePresenter;
import educatus.client.ui.CustomButton;

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

    /**
     * {@link SeminarHomePresenter}'s view.
     */
    public interface MyView extends View {
    	FlowPanel getCategoryPanel();	  
    }
  
    ListFadeAnimation<HasWidgets> listAnimation;

    @Inject
    public SeminarHomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy) {
      super(eventBus, view, proxy);
    }

  	@Override
	protected void revealInParent() {
	  RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
	}
  
	@Override
	protected void onBind() {
	  super.onBind();    
	  FlowPanel categoryPanel = getView().getCategoryPanel();
	  registerHandlers(categoryPanel);
	  animateButtonsIn(categoryPanel);
	}
  
  	private void animateButtonsIn(Widget panelWidget) {
	  listAnimation = new ListFadeAnimation<HasWidgets>((HasWidgets) panelWidget);
	  listAnimation.start(150, 0, 0.75);
  	}
  	
	private void registerHandlers(final FlowPanel panel) {
		Iterator<Widget> it = panel.iterator();
		CustomButton button;
		while(it.hasNext()){
			button = (CustomButton) it.next();
			registerHandler((button).addClickHandler(
					new ClickHandler() {
			            @Override
			            public void onClick(ClickEvent event) {
			            	transitionCategoryPanel(panel);
		                }
		            }));    
		}
	}  
  	
    private void rePopulateCategoryPanel(FlowPanel panel) {    	
		CustomButton button = new CustomButton();
		button.add(new Image("images/door_mind.png"));
		button.add(new Label("Salut"));		
		button.setEnabled(false);
		panel.add(button);
		
		button = new CustomButton();		
		button.add(new Image("images/Forums.png"));	
		button.add(new Label("Monsieur"));
		button.setEnabled(false);
		panel.add(button);
		
		button = new CustomButton();		
		button.add(new Image("images/door_mind.png"));	
		button.add(new Label("Nuage,"));
		button.setEnabled(false);
		panel.add(button);
		
		button = new CustomButton();
		button.add(new Image("images/earth_puzzle_3.png"));
		button.add(new Label("Ca"));		
		button.setEnabled(false);
		panel.add(button);
		
		button = new CustomButton();
		button.add(new Image("images/Forums.png"));	
		button.add(new Label("Va"));		
		button.setEnabled(false);
		panel.add(button);
		
		button = new CustomButton();
		button.add(new Image("images/earth_puzzle_3.png"));	
		button.add(new Label("Bien ?"));		
		button.setEnabled(false);
		panel.add(button);
		
		registerHandlers(panel);
		animateButtonsIn(panel);		
    }
    
  	private void transitionCategoryPanel(final FlowPanel panel) {
  		listAnimation.killAnimations();
  		panel.clear();
		
    	Timer timer = new Timer() {
    		public void run() {
    			rePopulateCategoryPanel(panel);
    		};
    	};
    	timer.schedule(250);
  	}
}