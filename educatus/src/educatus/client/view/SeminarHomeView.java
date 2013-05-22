package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminarHomePresenter.MyView;

public class SeminarHomeView extends ViewImpl implements MyView {
	  interface SeminarHomeUiBinder extends UiBinder<Widget, SeminarHomeView> {
	  }

	  private static SeminarHomeUiBinder uiBinder = GWT.create(SeminarHomeUiBinder.class);
	  public final Widget widget;

	  public SeminarHomeView() {
	    widget = uiBinder.createAndBindUi(this);
	  }

	  public Widget asWidget() {
	    return widget;
	  }
}