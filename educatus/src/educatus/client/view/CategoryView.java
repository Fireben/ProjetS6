package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.CategoryPresenter;

public class CategoryView extends ViewImpl implements
CategoryPresenter.MyView {

	public final Widget widget;
	
	@UiField FlowPanel categoryPanel;
	@UiField FlowPanel buttonPanel;
	@UiField Button backButton;
	@UiField Button nextButton;
	@UiField Label titleLabel;
	
	public interface Binder extends UiBinder<Widget, CategoryView> {
	}

	@Inject
	public CategoryView(final Binder binder) {	
	  widget = binder.createAndBindUi(this);
	  categoryPanel.setStyleName("categoryPanel");
	  buttonPanel.setStyleName("buttonPanel");  
	  backButton.setStyleName("backCategoryButton");
	  nextButton.setStyleName("nextCategoryButton");
	}
	
	public Widget asWidget() {
		return widget;
	}
	
	public FlowPanel getCategoryPanel() {
		return categoryPanel;
	}

	public FlowPanel getButtonPanel() {
		return buttonPanel;
	}	 
	
	public Button getButton() {
		return backButton;
	}
	
	public Label getLabel() {
		return titleLabel;
	}
}