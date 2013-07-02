package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class ProfilNewsfeed extends Composite {

	private static ProfilNewsfeedUiBinder uiBinder = GWT.create(ProfilNewsfeedUiBinder.class);

	interface ProfilNewsfeedUiBinder extends UiBinder<Widget, ProfilNewsfeed> {
	}

	public ProfilNewsfeed() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
