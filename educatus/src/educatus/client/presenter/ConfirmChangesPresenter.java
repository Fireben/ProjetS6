package educatus.client.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PushButton;

public class ConfirmChangesPresenter extends
		PresenterWidget<ConfirmChangesPresenter.MyView> {
	
	HandlerRegistration saveHandlerRegistration = null;
	HandlerRegistration addImageHandlerRegistration = null;
	HandlerRegistration addTextHandlerRegistration = null;
	HandlerRegistration addPdfHandlerRegistration = null;
	HandlerRegistration confirmHandlerRegistration = null;
	HandlerRegistration cancelHandlerRegistration = null;

	public interface MyView extends View {
		public Button getSaveButton();
		public Button getCancelButton();
		public PushButton getAddTextButton();
		public PushButton getAddImageButton();
		public PushButton getAddVideoButton();
		public PushButton getAddPdfButton();
	}

	@Inject
	public ConfirmChangesPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	public void setSaveButtonHandler(ClickHandler saveHandler) {
		if(saveHandlerRegistration == null) {
			saveHandlerRegistration = getView().getSaveButton().addClickHandler(saveHandler);	
		}
	}

	public void setAddImageHandler(ClickHandler addImageHandler) {
		if(addImageHandlerRegistration == null) {
			addImageHandlerRegistration = getView().getAddImageButton().addClickHandler(addImageHandler);	
		}
	}

	public void setAddTextHandler(ClickHandler addTextBoxHandler) {
		if(addTextHandlerRegistration == null) {
			addTextHandlerRegistration = getView().getAddTextButton().addClickHandler(addTextBoxHandler);	
		}
	}
	
	public void setAddPdfHandler(ClickHandler addPdfHandler) {
		if(addPdfHandlerRegistration == null) {
			addPdfHandlerRegistration = getView().getAddPdfButton().addClickHandler(addPdfHandler);	
		}
	}

	public void setConfirmHandler(ClickHandler confirmHandler) {
		if(confirmHandlerRegistration == null) {
			confirmHandlerRegistration = getView().getSaveButton().addClickHandler(confirmHandler);	
		}
	}

	public void setCancelHandler(ClickHandler cancelHandler) {
		if(cancelHandlerRegistration == null) {
			cancelHandlerRegistration = getView().getCancelButton().addClickHandler(cancelHandler);	
		}
	}
}
