package educatus.client.presenter;

import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.NameToken;
import educatus.client.NameTokens;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

public class SeminaryEditPresenter extends
		Presenter<SeminaryEditPresenter.MyView, SeminaryEditPresenter.MyProxy>
{
	public interface MyView extends View
	{
	}
	
	@ProxyCodeSplit
	@NameToken(NameTokens.seminaryedit)
	public interface MyProxy extends ProxyPlace<SeminaryEditPresenter>
	{
	}
	
	public static final Object SLOT_confirm = new Object();
	
	@Inject
	ConfirmChangesPresenter confirmPresenter;

	@Inject
	public SeminaryEditPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy)
	{
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent()
	{
		RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent, this);
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
		setInSlot(SLOT_confirm, confirmPresenter);
	}
}
