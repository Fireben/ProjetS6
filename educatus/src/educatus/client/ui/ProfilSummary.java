package educatus.client.ui;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ProfilSummary extends Composite {

	private static ProfilSummaryUiBinder uiBinder = GWT.create(ProfilSummaryUiBinder.class);
	
	@UiField
	Label profilSummaryLabel;
	@UiField
	Image profilImage;
	@UiField
	VerticalPanel userDescriptionVerticalPanel;
	@UiField
	VerticalPanel userImageVerticalPanel;
	
	private String imageId = null;
	private SingleUploader defaultUploader;
	private FlowPanel imageUploadPanel;
	
	interface ProfilSummaryUiBinder extends UiBinder<Widget, ProfilSummary> {
	}

	public ProfilSummary() {
		initWidget(uiBinder.createAndBindUi(this));
		profilSummaryLabel.setStyleName("profilSummaryLabel");
		profilImage.setStyleName("profilImage");
		userDescriptionVerticalPanel.setStyleName("userDescriptionVerticalPanel");
		
		defaultUploader = new SingleUploader();
		defaultUploader.setAutoSubmit(true);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);

		imageUploadPanel = new FlowPanel();
		imageUploadPanel.setStyleName("profilUploadPanel");
		imageUploadPanel.add(defaultUploader);
		
		userImageVerticalPanel.add(imageUploadPanel);
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
	
	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
				new PreloadedImage(uploader.fileUrl(), showImage);
				UploadedInfo info = uploader.getServerInfo();
				imageId = info.message;
			}
		}
	};
	
	private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		public void onLoad(PreloadedImage image) {
			//profilImage.setUrl(image.getUrl());
			profilImage.removeFromParent();
			imageUploadPanel.clear();
			imageUploadPanel.add(image);
			imageUploadPanel.add(defaultUploader);
		}
	};
}
