package educatus.client.ui.widget;

import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.SingleUploader;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;


public class PdfEdit extends EditSection {
	private FlowPanel pdfUploadPanel;
	private SingleUploader defaultUploader;
	private String pdfId = null;
	
	public PdfEdit() {
		super();	
		createPanel();
	}
	
	private void createPanel() {
		createUploader();
		
		titleLabel.setText("Pdf");
		
		panel.add(pdfUploadPanel);		
		panel.setStyleName("editSection");
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
}
