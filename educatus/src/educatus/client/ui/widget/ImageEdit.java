package educatus.client.ui.widget;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ImageEdit extends EditSection {
	private FlowPanel imageUploadPanel;
	private HorizontalPanel titlePanel;
	private TextBox titleBox;
	private SingleUploader defaultUploader;
	private String imageId = null;
	
	public ImageEdit() {
		super();
		createPanel();
	}
	
	private void createPanel() {
		createUploader();
		createTitle();
		
		titleLabel.setText("Image");
		
		panel.add(titlePanel);
		panel.add(imageUploadPanel);		
		panel.setStyleName("editSection");
	}	
	
	private void createTitle() {
		titlePanel = new HorizontalPanel();
		titlePanel.add(new Label("Title : "));
		titleBox = new TextBox();
		titleBox.setStyleName("editTitleTextBox");
		titlePanel.add(titleBox);		
		titlePanel.setStyleName("editTitle");
	}
	
	private void createUploader() {
		defaultUploader = new SingleUploader();
		defaultUploader.setAutoSubmit(true);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		
		imageUploadPanel = new FlowPanel();
		imageUploadPanel.setStyleName("imageUploadPanel");
		imageUploadPanel.add(defaultUploader);
	}
	
	public String getImageId() {
		return imageId;
	}
	
	public String getTitle() {
		return titleBox.getText();
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
			imageUploadPanel.clear();
			imageUploadPanel.add(image);
		}
	};
}
