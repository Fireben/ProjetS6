package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.ProfilPresenter;
import educatus.client.ui.ProfilNewsfeed;
import educatus.client.ui.ProfilSummary;

public class ProfilView extends ViewImpl implements ProfilPresenter.MyView {
	
	private final Widget widget;
	
	@UiField VerticalPanel profilVerticalPanel;
	@UiField ProfilSummary profilSummary;
	@UiField ProfilNewsfeed profilNewsfeed;
	
	public interface Binder extends UiBinder<Widget, ProfilView> {
	}
	
	@Inject
	public ProfilView(final Binder binder) {
	    widget = binder.createAndBindUi(this);
	    profilVerticalPanel.setStyleName("profilVerticalPanel");
	    profilSummary.setStyleName("profilSummary");
	    profilNewsfeed.setStyleName("profilNewsfeed");
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}
	public VerticalPanel getProfilVerticalPanel() {
		return profilVerticalPanel;
	}

	public ProfilSummary getProfilSummary() {
		return profilSummary;
	}

	public ProfilNewsfeed getProfilNewsfeed() {
		return profilNewsfeed;
	}
}
