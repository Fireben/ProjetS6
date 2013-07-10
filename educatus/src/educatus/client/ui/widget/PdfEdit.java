package educatus.client.ui.widget;

import educatus.client.ui.CustomButton;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


public class PdfEdit extends Composite {
	private FlowPanel panel;
	private FlowPanel pdfUploadPanel;
	private SingleUploader defaultUploader;
	private String pdfId = null;
	private CustomButton closeButton;
	
	public PdfEdit() {
		super();
		panel = new FlowPanel();
		initWidget(panel);		
		createPanel();
	}
	
	private void createPanel() {
		createCloseButton();
		createUploader();
		
		panel.add(closeButton);
		panel.add(pdfUploadPanel);		
		panel.setStyleName("editSection");
	}	
	
	private void createCloseButton() {
		closeButton = new CustomButton();
		closeButton.add(new Image("images/closeButton.png"));
		closeButton.setStyleName("editCloseButton");
	}
	
	private void createUploader() {
		defaultUploader = new SingleUploader();
		defaultUploader.setAutoSubmit(true);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
		
		pdfUploadPanel = new FlowPanel();
		pdfUploadPanel.setStyleName("pdfUploadPanel");
		pdfUploadPanel.add(defaultUploader);
	}
	
	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {
				UploadedInfo info = uploader.getServerInfo();
				showFile(info.name);
				pdfId = info.message;
			}
		}
	};
	
	public String getPdfId() {
		return pdfId;
	}

	private void showFile(String name) {
		panel.remove(pdfUploadPanel);
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.add(new Image("images/addPdf.png"));
		Label label = new Label(name);
		label.setStyleName("pdfPreviewLabel");
		horizontalPanel.add(label);		
		horizontalPanel.setStyleName("center");
		panel.add(horizontalPanel);
	}

	public CustomButton getCloseButton() {
		return closeButton;
	}
}
