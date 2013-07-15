package educatus.client.presenter;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.event.dom.client.ClickHandler;
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
import educatus.shared.dto.pagecontent.SeminaryHomePageCategoryContent;
import educatus.shared.dto.seminary.CategoryCoreContent;

public class CategoryPresenter extends PresenterWidget<CategoryPresenter.MyView> {
	ListFadeAnimation<HasWidgets> listAnimation = null;
	HandlerRegistration backRegisteredHandler = null;
	HandlerRegistration seeAllRegisteredHandler = null;
	
	private CategoryCoreContent parent = null; 
	
	public interface MyView extends View {
		FlowPanel getCategoryPanel();

		FlowPanel getButtonPanel();

		Button getButton();

		Button getSeeAllButton();

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

	public void animatePanelIn() {
		Label label = getView().getLabel();
		label.setVisible(true);
		FadeAnimation labelAnimation = new FadeAnimation(label, FadeAnimation.MIN_OPACITY, FadeAnimation.MAX_OPACITY, FadeAnimation.QUICK);
		labelAnimation.start();

		listAnimation = new ListFadeAnimation<HasWidgets>(getView().getButtonPanel());
		listAnimation.start(FadeAnimation.VERY_QUICK, FadeAnimation.MIN_OPACITY, FadeAnimation.MEDIUM_OPACITY);
	}

	public void animateBackButtonIn() {
		Button button = getView().getButton();
		button.setVisible(true);
		FadeAnimation buttonAnimation = new FadeAnimation(button, FadeAnimation.MIN_OPACITY, FadeAnimation.MEDIUM_OPACITY, FadeAnimation.QUICK);
		buttonAnimation.start();
	}

	public void clear() {
		if (listAnimation != null) {
			listAnimation.killAnimations();
		}
		getView().getButtonPanel().clear();
		getView().getButton().setVisible(false);
		getView().getLabel().setVisible(false);
	}

	public void populateCategoryPanel(SeminaryHomePageCategoryContent content) {
		FlowPanel buttonPanel = getView().getButtonPanel();
		CustomButton button = null;

		ArrayList<CategoryCoreContent> categories = content.getCategoryChildren();
		for (CategoryCoreContent category : categories) {
			button = CategoryButtonFactory.get(category.getName(), category.getImageUrl(), category.getId());
			buttonPanel.add(button);
		}

		this.parent = content.getCommonParent();
		if (parent == null) {
			getView().getLabel().setText("Seminaries");
		} else {
			getView().getLabel().setText(parent.getName());
		}

		// Le 4 représentent les margins left et right de 2px
		int buttonsWidth = categories.size() * (button.getOffsetWidth() + 4);
		getView().getCategoryPanel().setWidth(String.valueOf(buttonsWidth + 2) + "px");
	}

	public void registerBackButton(final ClickHandler backClickHandler) {
		if (backRegisteredHandler == null) {
			backRegisteredHandler = getView().getButton().addClickHandler(backClickHandler);
			registerHandler(backRegisteredHandler);
		}
	}

	public void registerSeeAllButton(final ClickHandler seeAllClickHandler) {
		if (seeAllRegisteredHandler == null) {
			seeAllRegisteredHandler = getView().getSeeAllButton().addClickHandler(seeAllClickHandler);
			registerHandler(seeAllRegisteredHandler);
		}
	}

	public void registerCategoryPanelHandlers(ClickHandler categoryClickHandler) {
		Iterator<Widget> it = getView().getButtonPanel().iterator();
		Widget widget;
		while (it.hasNext()) {
			widget = it.next();
			if (widget instanceof CustomButton) {
				registerHandler(((CustomButton) widget).addClickHandler(categoryClickHandler));
			}
		}
	}

	public void setAndAnimateCategoryPanel(final ClickHandler categoryClickHandler, final SeminaryHomePageCategoryContent content) {
		clear();

		populateCategoryPanel(content);
		registerCategoryPanelHandlers(categoryClickHandler);
		animatePanelIn();
	}
	
	public CategoryCoreContent getParent() {
		return this.parent;
	}
}
