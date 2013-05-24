package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.ProfilPresenter;

public class ProfilView extends ViewImpl implements ProfilPresenter.MyView {
	
	private final Widget widget;
	
	public interface Binder extends UiBinder<Widget, ProfilView> {
	}
	
	@Inject
	public ProfilView(final Binder binder) {
	    widget = binder.createAndBindUi(this);
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}
}
