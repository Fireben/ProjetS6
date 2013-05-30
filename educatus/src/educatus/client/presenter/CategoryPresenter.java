package educatus.client.presenter;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import educatus.client.animation.FadeAnimation;
import educatus.client.animation.ListFadeAnimation;
import educatus.client.ui.CustomButton;
import educatus.client.ui.factory.CategoryButtonFactory;

public class CategoryPresenter extends
		PresenterWidget<CategoryPresenter.MyView>
{
    ListFadeAnimation<HasWidgets> listAnimation = null;
    HandlerRegistration backRegisteredHandler = null;

	public interface MyView extends View {
    	FlowPanel getCategoryPanel();
    	FlowPanel getButtonPanel();
    	Button getButton();
    	Label getLabel();
	}

	@Inject
	public CategoryPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
	}	
	
	public void animatePanelIn(int state) {	  
	  if(state==1) {
		  Button button = getView().getButton();
		  button.setVisible(true);
		  FadeAnimation buttonAnimation = new FadeAnimation(button, FadeAnimation.MIN_OPACITY, FadeAnimation.MEDIUM_OPACITY, FadeAnimation.QUICK);
		  buttonAnimation.start();
	  }
	  
	  Label label = getView().getLabel();
	  label.setVisible(true);	  
	  FadeAnimation labelAnimation = new FadeAnimation(label, FadeAnimation.MIN_OPACITY, FadeAnimation.MAX_OPACITY, FadeAnimation.QUICK);
	  labelAnimation.start();
	  
	  listAnimation = new ListFadeAnimation<HasWidgets>(getView().getButtonPanel());	  
	  listAnimation.start(FadeAnimation.VERY_QUICK, FadeAnimation.MIN_OPACITY, FadeAnimation.MEDIUM_OPACITY);
  	} 
	
	public void clear() {
		if(listAnimation != null) {
			listAnimation.killAnimations();
		}
  		getView().getButtonPanel().clear();
  		getView().getButton().setVisible(false);
  		getView().getLabel().setVisible(false);
	}
  	
    public void populateCategoryPanel(int state) {  
    	FlowPanel buttonPanel = getView().getButtonPanel();
    	CustomButton button = null;
    	int count = 0;
    	if(state == 0) {    		
			button = CategoryButtonFactory.get("Languages", "images/categories/language.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Databases", "images/categories/database.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Algorithms", "images/categories/algorithm.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Math", "images/categories/math.png");
			buttonPanel.add(button);
			count = 4;
			getView().getLabel().setText("Seminaries");
    	}
    	else if(state == 1) {
			button = CategoryButtonFactory.get("Java", "images/langages/java.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("C++", "images/langages/c++.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Javascript", "images/langages/javascript.png");
			buttonPanel.add(button);
			
			//button = CategoryButtonFactory.get("C", "images/langages/c.png");
			count=3;
			getView().getLabel().setText("Languages");
    	}	
    	//Le 4 représentent les margins left et right de 2px
    	int buttonsWidth = count*(button.getOffsetWidth() + 4);
    	getView().getCategoryPanel().setWidth(String.valueOf(buttonsWidth+2)+"px");
    }
    
    public void initializeBackButton(final ClickHandler backClickHandler) {
    	if(backRegisteredHandler == null) {
	    	backRegisteredHandler = getView().getButton().addClickHandler(backClickHandler);
	    	registerHandler(backRegisteredHandler);
    	}
    }
    
	public void registerCategoryPanelHandlers(ClickHandler categoryClickHandler, int state) {
		Iterator<Widget> it = getView().getButtonPanel().iterator();
		Widget widget;
		while(it.hasNext()) {
			widget = it.next();
			if(widget instanceof CustomButton) {				
				registerHandler(((CustomButton)widget).addClickHandler(categoryClickHandler));   
			}
		}
	} 
	
	public void setAndAnimateCategoryPanel(final int state, final ClickHandler categoryClickHandler) {
		clear();
		Timer timer = new Timer() {
			 public void run() {
				 populateCategoryPanel(state);
				 registerCategoryPanelHandlers(categoryClickHandler, state);
				 animatePanelIn(state);
			 };
	 	};
	 	timer.schedule(250);
	}
}
