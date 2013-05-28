package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminarHomePresenter;
import educatus.client.presenter.SeminarHomePresenter.MyView;

public class SeminarHomeView extends ViewImpl implements MyView {
		interface SeminarHomeUiBinder extends UiBinder<Widget, SeminarHomeView> {
	}

    private static SeminarHomeUiBinder uiBinder = GWT.create(SeminarHomeUiBinder.class);
	public final Widget widget;
	
	@UiField HTMLPanel contentPanel;

	public SeminarHomeView() {		
	  widget = uiBinder.createAndBindUi(this);  
	}
	
	public Widget asWidget() {
		return widget;
	}	  
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		if(slot == SeminarHomePresenter.SLOT_content) {
			contentPanel.clear();
			if(content != null)
				contentPanel.add(content);
		}
		else
			super.setInSlot(slot, content);
	}
}