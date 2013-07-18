package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ShinyProgressBar extends Composite {

	private static ShinyProgressBarUiBinder uiBinder = GWT.create(ShinyProgressBarUiBinder.class);

	interface ShinyProgressBarUiBinder extends UiBinder<Widget, ShinyProgressBar> {
	}
	
	@UiField SpanElement span;

	public ShinyProgressBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	public void setProgressValue(int max, int current){
		int intPercentage = (int) (current / (double)max * 100.0);
		span.setAttribute("style", "width: " + intPercentage + "%");
	}
}
