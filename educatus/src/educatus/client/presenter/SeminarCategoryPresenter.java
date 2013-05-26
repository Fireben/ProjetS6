package educatus.client.presenter;

import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import educatus.client.animation.ListFadeAnimation;
import educatus.client.ui.CustomButton;
import educatus.client.ui.factory.CategoryButtonFactory;

public class SeminarCategoryPresenter extends
		PresenterWidget<SeminarCategoryPresenter.MyView>
{
    ListFadeAnimation<HasWidgets> listAnimation = null;

	public interface MyView extends View {
    	FlowPanel getCategoryPanel();
	}

	@Inject
	public SeminarCategoryPresenter(final EventBus eventBus, final MyView view) {
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
	  listAnimation = new ListFadeAnimation<HasWidgets>(getView().getCategoryPanel());
	  listAnimation.start(150, 0, 0.75);
  	} 
	
	public void clear() {
		if(listAnimation != null) {
			listAnimation.killAnimations();
		}
  		getView().getCategoryPanel().clear();
	}
  	
    public void populateCategoryPanel(int state) {  
    	FlowPanel panel = getView().getCategoryPanel();
    	if(state == 0) {
			CustomButton button = CategoryButtonFactory.get("Languages", "images/categories/language.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("Databases", "images/categories/database.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("Algorithms", "images/categories/algorithm.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("Math", "images/categories/math.png");
			panel.add(button);
    	}
    	else if(state == 1) {
			CustomButton button = CategoryButtonFactory.get("Java", "images/langages/java.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("C++", "images/langages/c++.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("Javascript", "images/langages/javascript.png");
			panel.add(button);
			
			button = CategoryButtonFactory.get("C", "images/langages/c.png");
			panel.add(button);
    	}		
    }
    
	public void registerCategoryPanelHandlers(ClickHandler categoryClickHandler) {
		Iterator<Widget> it = getView().getCategoryPanel().iterator();
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
