package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.CommunityPresenter;
import educatus.client.ui.CommunityUsersFeed;

public class CommunityView extends ViewImpl implements CommunityPresenter.MyView {
	
	private final Widget widget;
	
	@UiField VerticalPanel communityVerticalPanel;
	@UiField CommunityUsersFeed communityUsersFeed;
	
	public interface Binder extends UiBinder<Widget, CommunityView> {
	}
	
	@Inject
	public CommunityView(final Binder binder) {
	    widget = binder.createAndBindUi(this);
	    communityVerticalPanel.setStyleName("profilVerticalPanel");
	    communityUsersFeed.setStyleName("profilNewsfeed");
	}
	
	@Override
	public Widget asWidget() {
		return widget;
	}

	public VerticalPanel getCommunityVerticalPanel() {
		return communityVerticalPanel;
	}

	public void setCommunityVerticalPanel(VerticalPanel communityVerticalPanel) {
		this.communityVerticalPanel = communityVerticalPanel;
	}

	public CommunityUsersFeed getCommunityUsersFeed() {
		return communityUsersFeed;
	}

	public void setCommunityUsersFeed(CommunityUsersFeed communityUsersFeed) {
		this.communityUsersFeed = communityUsersFeed;
	}

	
}
