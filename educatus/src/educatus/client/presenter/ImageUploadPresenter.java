package educatus.client.presenter;

import gwtupload.client.IUploader;
import gwtupload.client.PreloadedImage;
import gwtupload.client.IUploadStatus.Status;
import gwtupload.client.IUploader.UploadedInfo;
import gwtupload.client.PreloadedImage.OnLoadPreloadedImageHandler;
import gwtupload.client.SingleUploader;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ImageUploadPresenter extends PresenterWidget<ImageUploadPresenter.MyView> {
	public interface MyView extends View {
		HTMLPanel getImageUploadPanel();
	}

	@Inject
	public ImageUploadPresenter(final EventBus eventBus, final MyView view) {
		super(eventBus, view);
	}
	
	// Load the image in the document and in the case of success attach it to
	// the viewer
	private IUploader.OnFinishUploaderHandler onFinishUploaderHandler = new IUploader.OnFinishUploaderHandler() {
		public void onFinish(IUploader uploader) {
			if (uploader.getStatus() == Status.SUCCESS) {

				new PreloadedImage(uploader.fileUrl(), showImage);
				// The server sends useful information to the client by default
				UploadedInfo info = uploader.getServerInfo();
				System.out.println("File name " + info.name);
				System.out.println("File content-type " + info.ctype);
				System.out.println("File size " + info.size);

				// You can send any customized message and parse it
				System.out.println("Server message " + info.message);
			}
		}
	};

	// Attach an image to the pictures viewer
	private OnLoadPreloadedImageHandler showImage = new OnLoadPreloadedImageHandler() {
		public void onLoad(PreloadedImage image) {
			getView().getImageUploadPanel().clear();
			getView().getImageUploadPanel().add(image);
		}
	};

	@Override
	protected void onBind() {
		super.onBind();
		SingleUploader defaultUploader = new SingleUploader();
		defaultUploader.setAutoSubmit(true);
		getView().getImageUploadPanel().add(defaultUploader);
		defaultUploader.addOnFinishUploadHandler(onFinishUploaderHandler);
	}

	@Override
	protected void onReset() {
		super.onReset();
	}

}
