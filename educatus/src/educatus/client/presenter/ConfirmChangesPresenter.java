package educatus.client.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.google.gwt.user.client.ui.Button;

public class ConfirmChangesPresenter extends
		PresenterWidget<ConfirmChangesPresenter.MyView>
{

	public interface MyView extends View
	{
		public Button getSaveButton();
		public Button getCancelButton();
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
