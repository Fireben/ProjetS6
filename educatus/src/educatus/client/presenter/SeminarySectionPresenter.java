package educatus.client.presenter;

import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

public class SeminarySectionPresenter extends
		PresenterWidget<SeminarySectionPresenter.MyView>
{
	public enum SeminaryContentType{
		Text,
		Image,
		Video;
	}
	
	public interface MyView extends View
	{
		public PushButton getDeleteBtn();
		public void createTextContent();
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
