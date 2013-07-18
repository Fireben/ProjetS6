package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserStatisticsWidget extends Composite {

	private static UserStatisticsWidgetUiBinder uiBinder = GWT.create(UserStatisticsWidgetUiBinder.class);

	@UiField
	Label userStatisticLabel;
	@UiField
	VerticalPanel userStatisticVerticalPanel;
	
	interface UserStatisticsWidgetUiBinder extends UiBinder<Widget, UserStatisticsWidget> {
	}

	public UserStatisticsWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		userStatisticLabel.setStyleName("userStatisticLabel");
		userStatisticVerticalPanel.setStyleName("userStatisticVerticalPanel");
	}
	
	public void appendProgressBar(String name, int maxValue, int currentValue){
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setWidth("100%");
		horizontalPanel.setSpacing(5);
		Label l = new Label(name);
		horizontalPanel.add(l);
		horizontalPanel.setCellWidth(l, "200px");
		
		ShinyProgressBar shinyProgressBar = new ShinyProgressBar();
		shinyProgressBar.setProgressValue(maxValue, currentValue);
		horizontalPanel.add(shinyProgressBar);
		horizontalPanel.setCellWidth(shinyProgressBar, "auto");
		
		userStatisticVerticalPanel.add(horizontalPanel);		
	}
	
	public void clearAllProgressBars(){
		userStatisticVerticalPanel.clear();
	}
}
