package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.EditButtonPanelPresenter;

public class EditButtonPanelView extends ViewImpl implements
		EditButtonPanelPresenter.MyView {
	private final Widget widget;

	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;
	@UiField
	HTMLPanel buttonPanel;
	@UiField
	HorizontalPanel addSectionPanel;

	public interface Binder extends UiBinder<Widget, EditButtonPanelView> {
	}

	@Inject
	public EditButtonPanelView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		DOM.getElementById("saveButton");
		buttonPanel.setStyleName("editButtonPanel");
		addSectionPanel.setStyleName("addSectionPanel");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public HorizontalPanel getAddSectionPanel() {
		return addSectionPanel;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}
}
