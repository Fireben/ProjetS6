package educatus.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilSummary extends Composite {

	private static ProfilSummaryUiBinder uiBinder = GWT.create(ProfilSummaryUiBinder.class);
	
	@UiField
	Label profilSummaryLabel;
	@UiField
	Image profilImage;
	@UiField
	VerticalPanel userDescriptionVerticalPanel;
	
	interface ProfilSummaryUiBinder extends UiBinder<Widget, ProfilSummary> {
	}

	public ProfilSummary() {
		initWidget(uiBinder.createAndBindUi(this));
		profilSummaryLabel.setStyleName("profilSummaryLabel");
		profilImage.setStyleName("profilImage");
		userDescriptionVerticalPanel.setStyleName("userDescriptionVerticalPanel");
	}
	public Label getProfilSummaryLabel() {
		return profilSummaryLabel;
	}

	public void setProfilSummaryLabel(Label profilSummaryLabel) {
		this.profilSummaryLabel = profilSummaryLabel;
	}

	public Image getProfilImage() {
		return profilImage;
	}

	public void setProfilImage(Image profilImage) {
		this.profilImage = profilImage;
	}

	public VerticalPanel getUserDescriptionVerticalPanel() {
		return userDescriptionVerticalPanel;
	}

	public void setUserDescriptionVerticalPanel(VerticalPanel userDescriptionVerticalPanel) {
		this.userDescriptionVerticalPanel = userDescriptionVerticalPanel;
	}
}
