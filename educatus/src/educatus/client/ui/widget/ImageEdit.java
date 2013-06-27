package educatus.client.ui.widget;

import educatus.client.ui.CustomButton;
import gwtupload.client.IUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.SingleUploader;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ImageEdit extends Composite {
	
	private FlowPanel panel;
	private FlowPanel imageUploadPanel;
	private HorizontalPanel titlePanel;
	private TextBox titleBox;
	private SingleUploader defaultUploader;
	private String imageId = null;
	private CustomButton closeButton;
	
	public ImageEdit() {
		super();
		panel = new FlowPanel();
		initWidget(panel);		
		createPanel();
	}
	
	private void createPanel() {
		createUploader();
		createTitle();
		createCloseButton();
		
		panel.add(closeButton);
		panel.add(titlePanel);
		panel.add(imageUploadPanel);		
		panel.setStyleName("editSection");
	}	
	
	private void createCloseButton() {
		closeButton = new CustomButton();
		closeButton.add(new Image("images/closeButton.png"));
		closeButton.setStyleName("editCloseButton");
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
	
	public CustomButton getCloseButton() {
		return closeButton;
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
