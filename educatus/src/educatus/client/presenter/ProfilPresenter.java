package educatus.client.presenter;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealContentEvent;

import educatus.client.NameTokens;
import educatus.client.events.PageChangingEvent;

public class ProfilPresenter extends
		Presenter<ProfilPresenter.MyView, ProfilPresenter.MyProxy> {

	public interface MyView extends View {
	}

	@ProxyCodeSplit
	@NameToken(NameTokens.profil)
	public interface MyProxy extends ProxyPlace<ProfilPresenter> {
	}
	
	@Inject
	public ProfilPresenter(final EventBus eventBus, final MyView view,
			final MyProxy proxy) {
		super(eventBus, view, proxy);
	}

	@Override
	protected void revealInParent() {
	    RevealContentEvent.fire(this, MainPagePresenter.TYPE_SetMainContent,
	            this);
	}
	
	@Override
	protected void onBind() {
		super.onBind();
	}
	
	@Override
	protected void onReset() {
		PageChangingEvent.fire(this, NameTokens.getProfil());
		super.onReset();
	}
}
