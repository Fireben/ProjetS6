package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.ProfilPresenter;

public class ProfilView extends ViewImpl implements ProfilPresenter.MyView {
	interface ProfilViewUiBinder extends UiBinder<Widget, ProfilView> {
	  }
	private final Widget widget;
	
	private static ProfilViewUiBinder uiBinder = GWT.create(ProfilViewUiBinder.class);

	public interface Binder extends UiBinder<Widget, ProfilView> {
	}

	public ProfilView() {
	    widget = uiBinder.createAndBindUi(this);
	}

	public Widget asWidget() {
		return widget;
	}
	
}
