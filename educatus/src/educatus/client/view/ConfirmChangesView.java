package educatus.client.view;

import com.gwtplatform.mvp.client.ViewImpl;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import educatus.client.presenter.ConfirmChangesPresenter;

public class ConfirmChangesView extends ViewImpl implements
		ConfirmChangesPresenter.MyView
{
	private final Widget widget;
	
	@UiField
	Button saveButton;
	@UiField
	Button cancelButton;

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
	
	public Button getSaveButton()
	{
		return saveButton;
	}
	
	public Button getCancelButton()
	{
		return cancelButton;
	}
	
}
