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
	  firstButton.add(new Image("images/earth_puzzle_3.png"));
	  firstButton.add(new Label("Databases"));	  
	  firstButton.setEnabled(false);
	  
	  secondButton = new CustomButton();
	  secondButton.add(new Image("images/earth_puzzle_3.png"));
	  secondButton.add(new Label("C++"));	  
	  secondButton.setEnabled(false);
	  
	  thirdButton = new CustomButton();
	  thirdButton.add(new Image("images/earth_puzzle_3.png"));
	  thirdButton.add(new Label("Javascript"));
	  
	  thirdButton.setEnabled(false);
	  
	  fourthButton = new CustomButton();
	  fourthButton.add(new Image("images/earth_puzzle_3.png"));
	  fourthButton.add(new Label("Nuage"));	  
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