package educatus.client.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class SeminarySectionPresenter extends
		PresenterWidget<SeminarySectionPresenter.MyView>
{

	public interface MyView extends View
	{
	}

	@Inject
	public SeminarySectionPresenter(final EventBus eventBus, final MyView view)
	{
		super(eventBus, view);
	}

	@Override
	protected void onBind()
	{
		super.onBind();
	}

	@Override
	protected void onReset()
	{
		super.onReset();
	}
}
