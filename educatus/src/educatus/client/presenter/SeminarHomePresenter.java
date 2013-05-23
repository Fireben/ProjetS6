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

import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;
import educatus.client.NameTokens;
import educatus.client.presenter.MainPagePresenter;
import educatus.client.ui.CustomButton;
import educatus.client.ui.FadeAnimation;

/**
 * @author Christian Goudreau
 */
public class SeminarHomePresenter extends
    Presenter<SeminarHomePresenter.MyView, SeminarHomePresenter.MyProxy> {
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
	  CustomButton getFirstButton();		
	  CustomButton getSecondButton(); 
	  CustomButton getThirdButton();
	  CustomButton getFourthButton(); 
  }

  @Inject
  public SeminarHomePresenter(final EventBus eventBus, final MyView view,
      final MyProxy proxy) {
    super(eventBus, view, proxy);
  }

  @Override
  protected void revealInParent() {
    RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
        this);
  }
  
  @Override
  protected void onBind() {
    super.onBind();
  }
  
  @Override
  protected void onReset() {
	  super.onReset();
	  
	  final FadeAnimation fourthFadeAnimation = new FadeAnimation(getView().getFourthButton().getElement(), 1.0, 750); 
	  
	  final FadeAnimation thirdFadeAnimation = new FadeAnimation(getView().getThirdButton().getElement(), 1.0, 750) {
		  public void onComplete() {
			  fourthFadeAnimation.start();
		  };
	  };
	  
	  final FadeAnimation secondFadeAnimation = new FadeAnimation(getView().getSecondButton().getElement(), 1.0, 750) {
		  public void onComplete() {
			  thirdFadeAnimation.start();
		  };
	  };
	  
	  FadeAnimation fadeAnimation = new FadeAnimation(getView().getFirstButton().getElement(), 1.0, 750) {
		  public void onComplete() {
			  secondFadeAnimation.start();
		  };
	  };
	  fadeAnimation.start();
  }
  
  protected void onHide() {
	  getView().getFirstButton().getElement().getStyle().setOpacity(0);
	  getView().getSecondButton().getElement().getStyle().setOpacity(0);
	  getView().getThirdButton().getElement().getStyle().setOpacity(0);
	  getView().getFourthButton().getElement().getStyle().setOpacity(0);
  }
  
}