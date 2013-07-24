package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminaryViewPresenter.MyView;
import educatus.client.ui.widget.DynamicSection;

public class SeminaryViewView extends ViewImpl implements MyView {

	public interface Binder extends UiBinder<Widget, SeminaryViewView> {
	}

	private final Widget widget;

	DynamicSection dynamicSection = new DynamicSection();	
	@UiField
	FlowPanel descriptionContainer;	
	@UiField
	FlowPanel statsContainer;	
	@UiField
	Button seenButton;	
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
		seenButton.setVisible(false);
		
		descriptionContainer.setStyleName("descriptionContainer");
		titleLabel.setStyleName("viewTitleLabel");
		contentContainer.setStyleName("viewContentContainer");	
		rightSpacer.setStyleName("viewSpacer");
		seenButton.setStyleName("seenButton");
		statsContainer.setStyleName("statsContainer");
		
		horizontalPanel.setCellWidth(leftSpacer, "45%");
		horizontalPanel.setCellWidth(rightSpacer, "5%");
		horizontalPanel.setCellWidth(descriptionContainer, "50%");
	}
	
	public FlowPanel getDescriptionContainer() {
		return descriptionContainer;
	}

	public DynamicSection getDynamicSection() {
		return dynamicSection;
	}	
	
	public FlowPanel getContentContainer() {
		return contentContainer;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public Button getSeenButton() {
		return seenButton;
	}

	public FlowPanel getStatsContainer() {
		return statsContainer;
	}

	public Widget asWidget() {
		return widget;
	}
}
