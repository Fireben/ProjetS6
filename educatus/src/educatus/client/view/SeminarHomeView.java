package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminarHomePresenter.MyView;
import educatus.client.ui.CustomButton;

public class SeminarHomeView extends ViewImpl implements MyView {
		interface SeminarHomeUiBinder extends UiBinder<Widget, SeminarHomeView> {
	}

    private static SeminarHomeUiBinder uiBinder = GWT.create(SeminarHomeUiBinder.class);
	public final Widget widget;
    @UiField CustomButton firstButton;
    @UiField CustomButton secondButton;
    @UiField CustomButton thirdButton;
    @UiField CustomButton fourthButton;

	public SeminarHomeView() {		
		widget = uiBinder.createAndBindUi(this);
	}
	
	public Widget asWidget() {
		return widget;
	}
	  
	public CustomButton getFirstButton() {
		return firstButton;
	}
	
	public CustomButton getSecondButton() {
		return secondButton;
	}
	
	public CustomButton getThirdButton() {
		return thirdButton;
	}
	
	public CustomButton getFourthButton() {
		return fourthButton;
	}
	  
}