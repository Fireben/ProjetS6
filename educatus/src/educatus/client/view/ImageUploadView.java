package educatus.client.view;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import educatus.client.presenter.ImageUploadPresenter;

public class ImageUploadView extends ViewImpl implements ImageUploadPresenter.MyView {
	public interface Binder extends UiBinder<Widget, ImageUploadView> {
	}
	
	@UiField
	HTMLPanel editImageSection;
	@UiField
	HTMLPanel imageUploadPanel;
	@UiField
	HorizontalPanel editTitle;	

	private final Widget widget;

	@Inject
	public ImageUploadView(final Binder binder) {
		widget = binder.createAndBindUi(this);
		imageUploadPanel.setStyleName("imageUploadPanel");
		editTitle.setStyleName("editTitle");
		editImageSection.setStyleName("editSection");
	}

	@Override
	public Widget asWidget() {
		return widget;
	}
	
	public HTMLPanel getImageUploadPanel() {
		return imageUploadPanel;
	}
}
