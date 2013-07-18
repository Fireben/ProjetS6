package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.ExerciceHomePresenter;
import educatus.client.presenter.ExerciceHomePresenter.MyView;

public class ExerciceHomeView extends ViewImpl implements MyView {
		interface ExerciceHomeUiBinder extends UiBinder<Widget, ExerciceHomeView> {
	}

    private static ExerciceHomeUiBinder uiBinder = GWT.create(ExerciceHomeUiBinder.class);
	public final Widget widget;
	
	@UiField HTMLPanel contentPanel;

	public ExerciceHomeView() {		
	  widget = uiBinder.createAndBindUi(this);  
	}
	
	public Widget asWidget() {
		return widget;
	}	  
	
	@Override
	public void setInSlot(Object slot, Widget content) {
		if(slot == ExerciceHomePresenter.SLOT_content) {
			contentPanel.clear();
			if(content != null)
				contentPanel.add(content);
		}
		else
			super.setInSlot(slot, content);
	}
}