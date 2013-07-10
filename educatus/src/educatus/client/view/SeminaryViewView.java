package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
	FlowPanel pdfSectionContainer;	
	@UiField
	FlowPanel descriptionContainer;	
	@UiField
	FlowPanel contentContainer;	
	@UiField
	Label titleLabel;
	@UiField
	FlowPanel leftSpacer;
	@UiField
	FlowPanel rightSpacer;
	@UiField
	HorizontalPanel horizontalPanel;

	@Inject
	public SeminaryViewView(final Binder binder) {		
		widget = binder.createAndBindUi(this);
		
		descriptionContainer.setVisible(false);
		contentContainer.setVisible(false);
		
		descriptionContainer.setStyleName("descriptionContainer");
		dynamicSectionContainer.setStyleName("dynamicSectionContainer");
		pdfSectionContainer.setStyleName("pdfSectionContainer");
		titleLabel.setStyleName("viewTitleLabel");
		contentContainer.setStyleName("viewContentContainer");	
		rightSpacer.setStyleName("viewSpacer");		
		
		horizontalPanel.setCellWidth(leftSpacer, "45%");
		horizontalPanel.setCellWidth(rightSpacer, "5%");
		horizontalPanel.setCellWidth(descriptionContainer, "50%");
	}
	
	public FlowPanel getDescriptionContainer() {
		return descriptionContainer;
	}

	public FlowPanel getDynamicSectionContainer() {
		return dynamicSectionContainer;
	}	
	
	public FlowPanel getPdfSectionContainer() {
		return pdfSectionContainer;
	}

	public FlowPanel getContentContainer() {
		return contentContainer;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public Widget asWidget() {
		return widget;
	}
}
