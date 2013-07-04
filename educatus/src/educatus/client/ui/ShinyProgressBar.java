package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ShinyProgressBar extends Composite {

	private static ShinyProgressBarUiBinder uiBinder = GWT.create(ShinyProgressBarUiBinder.class);

	interface ShinyProgressBarUiBinder extends UiBinder<Widget, ShinyProgressBar> {
	}

	public ShinyProgressBar() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public ShinyProgressBar(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
	}
}
