package educatus.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import educatus.client.presenter.SeminarHomePresenter.MyView;
import educatus.client.ui.CustomButton;

public class SeminarHomeView extends ViewImpl implements MyView {
		interface SeminarHomeUiBinder extends UiBinder<Widget, SeminarHomeView> {
	}

    private static SeminarHomeUiBinder uiBinder = GWT.create(SeminarHomeUiBinder.class);
	public final Widget widget;
	
	@UiField FlowPanel categoryPanel;
	CustomButton firstButton;
	CustomButton secondButton;
	CustomButton thirdButton;
	CustomButton fourthButton;

	public SeminarHomeView() {		
	  widget = uiBinder.createAndBindUi(this);
	  
	  firstButton = new CustomButton();
	  firstButton.add(new Label("Nuage"));
	  firstButton.add(new Image("images/Phone-HTC-Dash-icon-2.png"));
	  firstButton.setEnabled(false);
	  
	  secondButton = new CustomButton();
	  secondButton.add(new Label("Sauce"));
	  secondButton.add(new Image("images/Newspapers-1-icon-2.png"));
	  secondButton.setEnabled(false);
	  
	  thirdButton = new CustomButton();
	  thirdButton.add(new Label("Nuage"));
	  thirdButton.add(new Image("images/Phone-HTC-Dash-icon-2.png"));
	  thirdButton.setEnabled(false);
	  
	  fourthButton = new CustomButton();
	  fourthButton.add(new Label("Sauce"));
	  fourthButton.add(new Image("images/Newspapers-1-icon-2.png"));
	  fourthButton.setEnabled(false);
	  
	  categoryPanel.setStyleName("categoryPanel");
	  
	  categoryPanel.add(firstButton);
	  categoryPanel.add(secondButton);
	  categoryPanel.add(thirdButton);
	  categoryPanel.add(fourthButton);	  
	}
	
	public Widget asWidget() {
		return widget;
	}
	
	public FlowPanel getCategoryPanel() {
		return categoryPanel;
	}	  
}