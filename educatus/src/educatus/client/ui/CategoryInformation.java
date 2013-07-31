package educatus.client.ui;

import gwtupload.client.IUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.SingleUploader;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HeaderPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class CategoryInformation extends Composite {
	
	private static CategoryInformationUiBinder uiBinder = GWT.create(CategoryInformationUiBinder.class);
	
	@UiField
	HTMLPanel categoryEditHtmlPanel;
	@UiField
	VerticalPanel categoryEditVerticalPanel;	@UiField 
	TextBox categoryName; 
	@UiField 
	Image categoryImage;
	@UiField
	VerticalPanel categoryDescriptionVerticalPanel;
	@UiField
	VerticalPanel categoryImageVerticalPanel;
	
	private String imageId = null;
	private SingleUploader defaultUploader;
	private FlowPanel imageUploadPanel;
	
	interface CategoryInformationUiBinder extends UiBinder<Widget, CategoryInformation>{
	}

	public CategoryInformation(){
		initWidget(uiBinder.createAndBindUi(this));
		categoryName.setStyleName("editCategoryVerticalPanel");
		categoryEditHtmlPanel.setStyleName("editCategoryHtmlPanel");
		categoryName.setStyleName("editCategoryHtmlPanel");
		categoryImage.setStyleName("editCategoryImage");
		categoryDescriptionVerticalPanel.setStyleName("editCategoryDescriptionBox");
		defaultUploader = new SingleUploader();
		defaultUploader.setAutoSubmit(true);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);

		imageUploadPanel = new FlowPanel();
		imageUploadPanel.setStyleName("profilUploadPanel");
		imageUploadPanel.add(defaultUploader);
		
		categoryImageVerticalPanel.add(imageUploadPanel);
	}
	
	
	public TextBox getCategoryName(){
		return categoryName;
	}
	
	public void setCategoryName(TextBox categoryName){
		this.categoryName = categoryName;
	}
	
	public Image getCategoryImage(){
		return categoryImage;
	}
	
	public void setCategoryImage(Image categoryImage){
		this.categoryImage = categoryImage;
	}
	
	public VerticalPanel getCategoryDescriptionVecticalPanel(){
		return categoryDescriptionVerticalPanel;
	}
	
	public void setCategoryDescriptionVerticalPanel(VerticalPanel categoryDescription){
		this.categoryDescriptionVerticalPanel = categoryDescription;
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
			categoryImage.removeFromParent();
			imageUploadPanel.clear();
			imageUploadPanel.add(image);
			imageUploadPanel.add(defaultUploader);
		}
	};
}
