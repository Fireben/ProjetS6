package educatus.client.presenter;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

import educatus.client.ui.CustomButton;

public class EditButtonPanelPresenter extends
		PresenterWidget<EditButtonPanelPresenter.MyView> {
	
	HandlerRegistration saveHandlerRegistration = null;
	HandlerRegistration cancelHandlerRegistration = null;

	public interface MyView extends View {
		public Button getSaveButton();
		public Button getCancelButton();
		public HorizontalPanel getAddSectionPanel();
	}

	@Inject
	public EditButtonPanelPresenter(final EventBus eventBus, final MyView view) {
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

	public void setCancelHandler(ClickHandler cancelHandler) {
		if(cancelHandlerRegistration == null) {
			cancelHandlerRegistration = getView().getCancelButton().addClickHandler(cancelHandler);	
		}
	}
	
	public void addSectionButton(CustomButton addButton) {
		getView().getAddSectionPanel().add(addButton);		
	}

	public void addSectionButton(String url, ClickHandler clickHandler) {
		CustomButton addButton = new CustomButton();
		addButton.setStyleName("addSectionButton");
		addButton.add(new Image(url));
		addButton.addClickHandler(clickHandler);
		getView().getAddSectionPanel().add(addButton);		
	}
}
