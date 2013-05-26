package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminarCategoryPresenter;

public class SeminarCategoryView extends ViewImpl implements
SeminarCategoryPresenter.MyView {

	public final Widget widget;
	
	@UiField FlowPanel categoryPanel;
	
	public interface Binder extends UiBinder<Widget, SeminarCategoryView> {
	}

	@Inject
	public SeminarCategoryView(final Binder binder) {		
	  widget = binder.createAndBindUi(this);
	  categoryPanel.setStyleName("categoryPanel");	  
	}
	
	public Widget asWidget() {
		return widget;
	}
	
	public FlowPanel getCategoryPanel() {
		return categoryPanel;
	}	  
}