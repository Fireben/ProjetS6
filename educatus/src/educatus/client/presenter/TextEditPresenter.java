package educatus.client.presenter;

import com.google.gwt.user.client.ui.PushButton;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class TextEditPresenter extends PresenterWidget<TextEditPresenter.MyView> {
	public interface MyView extends View {
		public PushButton getDeleteBtn();
		public void createTextContent();
	}

	@Inject
	public TextEditPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}

	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

}
