package educatus.client.presenter;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
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

	public interface MyView extends View {
    	FlowPanel getCategoryPanel();
    	FlowPanel getButtonPanel();
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
	
	public void animatePanelIn() {
	  listAnimation = new ListFadeAnimation<HasWidgets>(getView().getButtonPanel());
	  listAnimation.start(FadeAnimation.QUICK, FadeAnimation.MIN_OPACITY, FadeAnimation.MEDIUM_OPACITY);
  	} 
	
	public void clear() {
		if(listAnimation != null) {
			listAnimation.killAnimations();
		}
  		getView().getButtonPanel().clear();
	}
  	
    public void populateCategoryPanel(int state) {  
    	FlowPanel buttonPanel = getView().getButtonPanel();
    	FlowPanel panel = getView().getCategoryPanel();
    	if(state == 0) {    		
			CustomButton button = CategoryButtonFactory.get("Languages", "images/categories/language.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Databases", "images/categories/database.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Algorithms", "images/categories/algorithm.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Math", "images/categories/math.png");
			buttonPanel.add(button);
    	}
    	else if(state == 1) {
			CustomButton button = CategoryButtonFactory.get("Java", "images/langages/java.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("C++", "images/langages/c++.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("Javascript", "images/langages/javascript.png");
			buttonPanel.add(button);
			
			button = CategoryButtonFactory.get("C", "images/langages/c.png");
			buttonPanel.add(button);
    	}	
    	panel.add(buttonPanel);
    }
    
	public void registerCategoryPanelHandlers(ClickHandler categoryClickHandler) {
		Iterator<Widget> it = getView().getButtonPanel().iterator();
		Widget widget;
		while(it.hasNext()) {
			widget = it.next();
			if(widget instanceof CustomButton) {				
				registerHandler(((CustomButton)widget).addClickHandler(categoryClickHandler));   
			}
		}
	} 
	
	public void setAndAnimateCategoryPanel(final int state, final ClickHandler clickHandler) {
		clear();
		Timer timer = new Timer() {
			 public void run() {
				 populateCategoryPanel(state);
				 registerCategoryPanelHandlers(clickHandler);
				 animatePanelIn();
			 };
	 	};
	 	timer.schedule(250);
	}
}