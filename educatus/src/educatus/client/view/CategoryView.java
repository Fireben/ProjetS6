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
	Button button;
	Label label;
	
	public interface Binder extends UiBinder<Widget, CategoryView> {
	}

	@Inject
	public CategoryView(final Binder binder) {	
	  widget = binder.createAndBindUi(this);
	  categoryPanel.setStyleName("categoryPanel");
	  buttonPanel.setStyleName("buttonPanel");
	  button = new Button("Back");
	  label = new Label("Seminars");
	  categoryPanel.add(label);
	  categoryPanel.add(buttonPanel);
	  categoryPanel.add(button);	  
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
		return button;
	}
	
	public Label getLabel() {
		return label;
	}
}