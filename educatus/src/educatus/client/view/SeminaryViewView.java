package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminaryViewPresenter.MyView;

public class SeminaryViewView extends ViewImpl implements MyView {

	public interface Binder extends UiBinder<Widget, SeminaryViewView> {
	}

	private final Widget widget;

	@UiField
	FlowPanel dynamicSectionContainer;	
	@UiField
	FlowPanel descriptionContainer;	
	@UiField
	FlowPanel seminaryContainer;	
	@UiField
	Label titleLabel;

	@Inject
	public SeminaryViewView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		
		descriptionContainer.setVisible(false);
		seminaryContainer.setVisible(false);
		
		descriptionContainer.setStyleName("descriptionContainer");
		dynamicSectionContainer.setStyleName("dynamicSectionContainer");		
		titleLabel.setStyleName("seminaryTitleLabel");
		seminaryContainer.setStyleName("viewSeminaryContainer");		
	}
	
	public FlowPanel getDescriptionContainer() {
		return descriptionContainer;
	}

	public FlowPanel getDynamicSectionContainer() {
		return dynamicSectionContainer;
	}		

	public FlowPanel getSeminaryContainer() {
		return seminaryContainer;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public Widget asWidget() {
		return widget;
	}
}
