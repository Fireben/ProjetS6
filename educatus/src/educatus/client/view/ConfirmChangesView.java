package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.ConfirmChangesPresenter;

public class ConfirmChangesView extends ViewImpl implements
		ConfirmChangesPresenter.MyView {
	private final Widget widget;

	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;
	@UiField
	PushButton addTextButton;
	@UiField
	PushButton addImageButton;
	@UiField
	PushButton addVideoButton;
	@UiField
	PushButton addPdfButton;
	@UiField
	HTMLPanel confirmPanel;
	@UiField
	HorizontalPanel addSectionPanel;

	public interface Binder extends UiBinder<Widget, ConfirmChangesView> {
	}

	@Inject
	public ConfirmChangesView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		DOM.getElementById("saveButton");
		confirmPanel.setStyleName("confirmPanel");
		addSectionPanel.setStyleName("addSectionPanel");
		addPdfButton.addStyleName("addPushButton");
		addTextButton.addStyleName("addPushButton");
		addImageButton.addStyleName("addPushButton");
		addVideoButton.addStyleName("addPushButton");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public Button getCancelButton() {
		return cancelButton;
	}

	public PushButton getAddTextButton() {
		return addTextButton;
	}

	public PushButton getAddImageButton() {
		return addImageButton;
	}

	public PushButton getAddVideoButton() {
		return addVideoButton;
	}
	
	public PushButton getAddPdfButton() {
		return addPdfButton;
	}
}
