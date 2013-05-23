package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.dom.client.ButtonElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.ConfirmChangesPresenter;

public class ConfirmChangesView extends ViewImpl implements
		ConfirmChangesPresenter.MyView
{
	private final Widget widget;
	
	ButtonElement saveButton;
	ButtonElement cancelButton;

	public interface Binder extends UiBinder<Widget, ConfirmChangesView>
	{
	}

	@Inject
	public ConfirmChangesView(final Binder binder)
	{
		widget = binder.createAndBindUi(this);
		DOM.getElementById("saveButton");
	}

	@Override
	public Widget asWidget()
	{
		return widget;
	}
	
	public ButtonElement getSaveButton()
	{
		return saveButton;
	}
	
	public ButtonElement getCancelButton()
	{
		return cancelButton;
	}
	
}
