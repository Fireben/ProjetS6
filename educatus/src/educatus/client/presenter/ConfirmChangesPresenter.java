package educatus.client.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.dom.client.ButtonElement;

public class ConfirmChangesPresenter extends
		PresenterWidget<ConfirmChangesPresenter.MyView>
{

	public interface MyView extends View
	{
		public ButtonElement getSaveButton();
		public ButtonElement getCancelButton();
	}

	@Inject
	public ConfirmChangesPresenter(final EventBus eventBus, final MyView view)
	{
		super(eventBus, view);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
		
	}
}
